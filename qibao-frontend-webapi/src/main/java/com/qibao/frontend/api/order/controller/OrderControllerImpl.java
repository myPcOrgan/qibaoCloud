package com.qibao.frontend.api.order.controller;

import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.BeanMapper;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IOrderFeign;
import com.qibao.frontend.feign.IUserInfoFeign;
import com.qibao.order.content.OrderDTO;
import com.qibao.order.enums.OrderType;
import com.qibao.order.vo.OrderUserVO;
import com.qibao.order.vo.OrderVO;
import com.qibao.user.context.dto.UpdateGoldInfoDTO;
import com.qibao.user.context.enums.GoldTypeEnum;
import com.qibao.user.context.vo.UserAccountVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * Created by ljn on 2018/1/12.
 */
@RestController
@RequestMapping("api/order")
public class OrderControllerImpl extends BaseController{

    @Autowired
    private IOrderFeign orderFeign;

    @Autowired
    private IUserInfoFeign userInfoFeign;

    @RequestMapping(value="/createOrder", method= RequestMethod.POST)
    @ApiOperation(value = "创建订单", notes="创建订单")
    public BaseResponse<OrderVO> createOrder(@RequestBody OrderDTO orderDTO) {
        BaseResponse<OrderVO> response = new BaseResponse();
        Long userId = UserContext.getCurrentUserId();
        if (userId == null) {
            response.setErrorMessage("用户未登录");
            return response;
        }
        UserAccountVO userAccountVO = userInfoFeign.getUserInfo(userId).getResult();
        if (userAccountVO == null) {
            response.setErrorMessage("不存在该用户");
            return response;
        }
        orderDTO.setUserId(userId);
        orderDTO.setUserAccount(userAccountVO.getUserAccount());
        return orderFeign.createOrder(orderDTO);
    }

    @RequestMapping(value="/queryOrderList", method= RequestMethod.POST)
    @ApiOperation(value = "查询的订单列表", notes="查询的订单列表")
    public BaseResponse<OrderVO> queryOrderList(@RequestBody OrderDTO orderDTO) throws ParseException {
        BaseResponse<OrderVO> response = new BaseResponse();
        Long userId = UserContext.getCurrentUserId();
        if (userId == null) {
            response.setErrorMessage("用户未登录");
            return response;
        }
        orderDTO.setUserId(userId);
        return orderFeign.queryOrderList(orderDTO);
    }

    @RequestMapping(value="/queryOrderByOrderNo", method= RequestMethod.GET)
    @ApiOperation(value = "根据订单编号获取订单信息", notes="根据订单编号获取订单信息")
    public BaseResponse<OrderUserVO> queryOrderByOrderNo(@RequestParam(name="orderNo",required = true)String orderNo) {
        BaseResponse<OrderUserVO> response = new BaseResponse();
        Long userId = UserContext.getCurrentUserId();
        if (userId == null) {
            response.setErrorMessage("用户未登录");
            return response;
        }
        OrderVO orderVO = orderFeign.queryOrderByOrderNo(orderNo).getResult();
        if (orderVO == null) {
            response.setErrorMessage("该订单不存在");
            return response;
        }
        OrderUserVO orderUserVO = BeanMapper.map(orderVO, OrderUserVO.class);
        UserAccountVO userAccountVO = userInfoFeign.getUserInfo(userId).getResult();
        if (orderUserVO != null) {
            orderUserVO.setUserImg(userAccountVO.getUserImg());
            orderUserVO.setNickName(userAccountVO.getNickName());
            orderUserVO.setUserAccount(userAccountVO.getUserAccount());
            orderUserVO.setUserGrade(userAccountVO.getUserGrade());
        }
        response.setResult(orderUserVO);
        return response;
    }

    @RequestMapping(value="/cancelOrder", method= RequestMethod.GET)
    @ApiOperation(value = "取消订单", notes="取消订单")
    public BaseResponse<OrderVO> cancelOrder(@RequestParam (name="orderNo",required = true) String orderNo) {
        BaseResponse<OrderVO> response = new BaseResponse();
        Long userId = UserContext.getCurrentUserId();
        if (userId == null) {
            response.setErrorMessage("用户未登录");
            return response;
        }
        return orderFeign.cancelOrder(orderNo);
    }

    @RequestMapping(value="/tradeSuccess", method= RequestMethod.GET)
    @ApiOperation(value = "交易成功(商城购买，支付回调)", notes="交易成功(商城购买，支付回调)")
    public BaseResponse tradeSuccess(@RequestParam (name="orderNo",required = true) String orderNo) {
        BaseResponse<OrderVO> response = new BaseResponse();
        BaseResponse<OrderVO> orderResponse = orderFeign.tradeSuccess(orderNo);
        OrderVO orderVO = orderResponse.getResult();
        if ("00".equals(orderResponse.getCode())) {
            UpdateGoldInfoDTO dto = new UpdateGoldInfoDTO();
            dto.setType(GoldTypeEnum.ADDTOTALGOLD.getCode());
            dto.setGoldNum(orderVO.getDealNum());
            dto.setUserId(orderVO.getUserId());
            dto.setDesp("您已购买"+orderVO.getDealNum()+orderVO.getCurrencyUnit());
            return userInfoFeign.updateUserGoldInfos(dto);
        }else {
            response.setErrorMessage(orderResponse.getMessage());
            return response;
        }
    }

    @RequestMapping(value="/mallAssignRole",method = RequestMethod.GET)
    @ApiOperation(value="金币商城分配角色回调", notes="金币商城分配角色回调")
    public BaseResponse<OrderVO> mallAssignRole(@RequestParam(name="orderNo",required = true)String orderNo) {
        return orderFeign.mallAssignRole(orderNo);
    }

    @RequestMapping(value="/mallTradeSuccess",method = RequestMethod.GET)
    @ApiOperation(value="金币商城交易成功回调",notes="金币商城交易成功回调")
    public BaseResponse mallTradeSuccess(@RequestParam(name="orderNo",required = true)String orderNo,
                                           @RequestParam(name="dealNum",required = true)Double dealNum){
        BaseResponse response = new BaseResponse();
        BaseResponse<OrderVO> orderResponse = orderFeign.mallTradeSuccess(orderNo, dealNum);
        OrderVO orderVO = orderResponse.getResult();
        if (orderResponse.getCode().equals("00")) {
            UpdateGoldInfoDTO dto = new UpdateGoldInfoDTO();
            dto.setGoldNum(orderVO.getDealNum());
            dto.setUserId(orderVO.getUserId());
            if (orderVO.getOrderType() == OrderType.DEPOSIT.getCode()) {
                dto.setType(GoldTypeEnum.ADDTOTALGOLD.getCode());
                dto.setDesp("您已成功存入"+orderVO.getDealNum()+orderVO.getCurrencyUnit());
            }else if (orderVO.getOrderType() == OrderType.GET_BACK.getCode()) {
                dto.setType(GoldTypeEnum.REDUCETOTALGOLD.getCode());
                dto.setDesp("您已成功取出"+orderVO.getDealNum()+orderVO.getCurrencyUnit());
            }
            return userInfoFeign.updateUserGoldInfos(dto);
        }else{
            response.setErrorMessage(orderResponse.getMessage());
            return response;
        }
    }
}
