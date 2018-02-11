package com.qibao.order.controller;

import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.DateUtil;
import com.qibao.common.utils.ParamUtils;
import com.qibao.order.content.OrderDTO;
import com.qibao.order.entity.OrderEO;
import com.qibao.order.enums.OrderStatus;
import com.qibao.order.enums.OrderType;
import com.qibao.order.exception.OrderException;
import com.qibao.order.service.IOrderController;
import com.qibao.order.service.IOrderService;
import com.qibao.order.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by ljn on 2018/1/10.
 * 订单服务
 */
@RestController
@RequestMapping("order")
public class OrderControllerImpl extends BaseController implements IOrderController {

    @Autowired
    private IOrderService orderService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value="createOrder",method = RequestMethod.POST)
    @ApiOperation(value="创建订单", notes="创建订单")
    public BaseResponse<OrderVO> createOrder(@RequestBody OrderDTO orderDTO){
        BaseResponse<OrderVO> response = new BaseResponse();

        if (orderDTO.getOrderType() == null) {
            throw new OrderException("订单类型不能为空");
        }
        if (orderDTO.getOrderType() == OrderType.GET_BACK.getCode() &&
                (orderDTO.getPoundage() == null || orderDTO.getPoundage() == 0)) {
            throw new OrderException("订单类型为取回时，手续费不能为空");
        }
        //订单类型为存入和取回时，游戏角色,商品名称,通货数量和商品分类
        if ((orderDTO.getOrderType() == OrderType.DEPOSIT.getCode() || orderDTO.getOrderType() == OrderType.GET_BACK.getCode())) {
            if (StringUtils.isBlank(orderDTO.getGameRole()) || StringUtils.isBlank(orderDTO.getRegionServerName()) ||
                    orderDTO.getCurrencyNum() == null || orderDTO.getCateId() == null){
                throw new OrderException("订单类型为存入和取回时，游戏角色,区服名称,通货数量和商品分类不能为空");
            }
        }
        //订单类型为商城购买时，金额数量不能为空，商品id不能为空
        if (orderDTO.getOrderType() == OrderType.MALL_BUY.getCode() && (orderDTO.getAmountMoney() == null ||
                orderDTO.getAmountMoney() == 0 || orderDTO.getGoodsId() == null)) {
            throw new OrderException("订单类型为商城购买时，金额不能为空,商品id不能为空");
        }
        OrderEO orderEO = BeanMapper.map(orderDTO, OrderEO.class);
        orderService.createOrder(orderEO);
        OrderVO orderVO = BeanMapper.map(orderEO, OrderVO.class);
        LOGGER.info("创建订单成功");
        response.setResult(orderVO);
        return response;
    }

    /**
     * 查询订单列表
     * @param orderDTO
     * @return
     */
    @RequestMapping(value="queryOrderList",method = RequestMethod.POST)
    @ApiOperation(value="查询订单列表", notes="查询订单列表")
    public BaseResponse<OrderVO> queryOrderList(@RequestBody OrderDTO orderDTO) throws ParseException {
        BaseResponse<OrderVO> response = new BaseResponse();
        if (orderDTO.getPage() == null) {
            orderDTO.setPage(1);
        }
        if (orderDTO.getSize() == null) {
            orderDTO.setSize(10);
        }
        OrderEO orderEO = new OrderEO();
        orderEO.setUserAccount(orderDTO.getUserAccount());
        orderEO.setOrderType(orderDTO.getOrderType());
        orderEO.setOrderStatus(orderDTO.getOrderStatus());
        orderEO.setMallOrderId(orderDTO.getMallOrderId());
        orderEO.setOrderNo(orderDTO.getOrderNo());
        Map<String, Object> params = ParamUtils.convertMap(orderEO);
        if (StringUtils.isNotBlank(orderDTO.getCreateStartTime())) {
            params.put("createStartTime",sdf.parse(orderDTO.getCreateStartTime()));
        }
        if (StringUtils.isNotBlank(orderDTO.getCreateEndTime())) {
            params.put("createEndTime", DateUtil.oneDateLastTime(sdf.parse(orderDTO.getCreateEndTime())));
        }
        params.put("isDeleted",false);
        int count = orderService.countByMap(params);
        List<OrderVO> list = orderService.queryOrderList(params,orderDTO.getPage(),orderDTO.getSize(),"create_time",true);
        response.setData(list);
        response.setTotalCount(count);
        response.setPageIndex(orderDTO.getPage());
        response.setPageSize(orderDTO.getSize());
        return response;
    }

    /**
     * 根据订单id查询订单详情
     * @param orderNo
     * @return
     */
    @RequestMapping(value="queryOrderByOrderNo",method = RequestMethod.GET)
    @ApiOperation(value="根据订单号查询订单", notes="根据订单号查询订单")
    public BaseResponse<OrderVO> queryOrderByOrderNo(@RequestParam(name="orderNo",required = true) String orderNo){
        BaseResponse<OrderVO> response = new BaseResponse();
        if (StringUtils.isBlank(orderNo)) {
            throw new OrderException("订单编号不能为空");
        }
        OrderEO orderEO = orderService.queryOrderByOrderId(orderNo);
        OrderVO orderVO = BeanMapper.map(orderEO, OrderVO.class);
        response.setResult(orderVO);
        return response;
    }

    /**
     * 取消订单
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "cancelOrder",method = RequestMethod.GET)
    @ApiOperation(value="取消订单", notes="取消订单")
    public BaseResponse<OrderVO> cancelOrder(@RequestParam(name="orderNo",required = true) String orderNo){
        BaseResponse<OrderVO> response = new BaseResponse();
        if (StringUtils.isBlank(orderNo)) {
            throw new OrderException("订单编号不能为空");
        }
        orderService.cancelOrder(orderNo);
        return response;
    }

    /**
     * 商城购买，交易成功
     * @param orderNo
     * @return
     */
    @RequestMapping(value="tradeSuccess",method = RequestMethod.GET)
    @ApiOperation(value="交易成功", notes="交易成功")
    public BaseResponse<OrderVO> tradeSuccess(@RequestParam(name="orderNo",required = true)String orderNo){
        BaseResponse<OrderVO> response = new BaseResponse();
        if (StringUtils.isBlank(orderNo)) {
            throw new OrderException("订单编号不能为空");
        }
        OrderVO orderVO = orderService.tradeSuccess(orderNo);
        response.setResult(orderVO);
        return response;
    }

    /**
     * 金币商城分配角色回调
     * @param orderNo
     * @return
     */
    @RequestMapping(value="mallAssignRole",method = RequestMethod.GET)
    @ApiOperation(value="金币商城分配角色回调", notes="金币商城分配角色回调")
    public BaseResponse<OrderVO> mallAssignRole(@RequestParam(name="orderNo",required = true)String orderNo) {
        BaseResponse<OrderVO> response = new BaseResponse();
        if (StringUtils.isBlank(orderNo)) {
            throw new OrderException("订单编号不能为空");
        }
        OrderVO orderVO = orderService.mallAssignRole(orderNo);
        response.setResult(orderVO);
        return response;
    }

    /**
     * 测试HttpClient接口
     * @param orderDTO
     * @return
     */
    @RequestMapping(value="informMall",method = RequestMethod.POST)
    @ApiOperation(value="通知商城", notes="通知商城")
    public BaseResponse<OrderVO> informMall(@RequestBody OrderDTO orderDTO) {
        BaseResponse response = new BaseResponse();
        try{
            response.setResult("订单号：SH00000001");
            return response;
        }catch (BaseException e) {
            response.setErrorMessage(e.getErrorMsg());
            return response;
        }catch (Exception e) {
            response.setErrorMessage(e.getMessage());
            return response;
        }
    }

    /**
     * 金币商城交易成功回调
     * @param orderNo
     * @param dealNum
     * @return
     */
    @RequestMapping(value="mallTradeSuccess",method = RequestMethod.GET)
    @ApiOperation(value="金币商城交易成功回调",notes="金币商城交易成功回调")
    @Override
    public BaseResponse<OrderVO> mallTradeSuccess(@RequestParam("orderNo") String orderNo,@RequestParam("dealNum") Double dealNum) {
        BaseResponse<OrderVO> response = new BaseResponse();
        if (StringUtils.isBlank(orderNo)) {
            throw new OrderException("订单编号不能为空");
        }
        if (dealNum == null) {
            throw new OrderException("实际交易数量不能为空");
        }
        OrderVO orderVO = orderService.mallTradeSuccess(orderNo,dealNum);
        response.setResult(orderVO);
        return response;
    }

    @RequestMapping(value="selectBuyGoldNum",method = RequestMethod.GET)
    @ApiOperation(value="平台用户购买的金币总数",notes="平台用户购买的金币总数")
    @Override
    public BaseResponse<Double> selectBuyGoldNum(@RequestParam("userType")Integer userType,
                                                 @RequestParam("startTime")String startTime,
                                                 @RequestParam("endTime")String endTime) throws ParseException {
        BaseResponse<Double> response = new BaseResponse<>();
        if (userType == null) {
            throw new OrderException("用户类型不能为空");
        }
        Map<String,Object> map = new HashedMap();
        if (StringUtils.isNotBlank(startTime)){
            map.put("startTime", format.parse(startTime));
        }
        if (StringUtils.isNotBlank(endTime)) {
            map.put("endTime",format.parse(endTime));
        }
        map.put("orderType",OrderType.MALL_BUY.getCode());
        map.put("orderStatus", OrderStatus.SUCCESS.getCode());
        if (userType == 2) {
            //用户类型为官方
            map.put("official",userType);
        }else{
            //用户类型为普通用户
            map.put("ordinary","1,3");
        }
        double num = orderService.selectBuyGoldNum(map);
        response.setResult(num);
        return response;
    }

    @RequestMapping(value="selectMallTradeGold",method = RequestMethod.GET)
    @ApiOperation(value="查询金币商城交易金币(包括存入，取回)",notes="查询金币商城交易金币(包括存入，取回)")
    public BaseResponse<Double> selectMallTradeGold(@RequestParam("orderType")Integer orderType,
                                                    @RequestParam("startTime")String startTime,
                                                    @RequestParam("endTime")String endTime) throws ParseException {
        BaseResponse<Double> response = new BaseResponse<>();
        Map<String,Object> map = new HashedMap();
        if (StringUtils.isNotBlank(startTime)){
            map.put("startTime", format.parse(startTime));
        }
        if (StringUtils.isNotBlank(endTime)) {
            map.put("endTime",format.parse(endTime));
        }
        map.put("orderType",OrderType.getOrderType(orderType).getCode());
        map.put("orderStatus", OrderStatus.SUCCESS.getCode());
        double num = orderService.selectMallTradeGold(map);
        response.setResult(num);
        return response;
    }

    @Override
    @RequestMapping(value="selectPoundage",method = RequestMethod.GET)
    @ApiOperation(value="查询手续费",notes="查询手续费")
    public BaseResponse<Double> selectPoundage(@RequestParam("startTime")String startTime,
                                               @RequestParam("endTime")String endTime) throws ParseException {
        BaseResponse<Double> response = new BaseResponse<>();
        Map<String,Object> map = new HashedMap();
        if (StringUtils.isNotBlank(startTime)){
            map.put("startTime", format.parse(startTime));
        }
        if (StringUtils.isNotBlank(endTime)) {
            map.put("endTime",format.parse(endTime));
        }
        map.put("orderType",OrderType.GET_BACK.getCode());
        map.put("orderStatus", OrderStatus.SUCCESS.getCode());
        double num = orderService.selectPoundage(map);
        response.setResult(num);
        return response;
    }

    @RequestMapping(value="additionalOrder",method = RequestMethod.GET)
    @ApiOperation(value="补单",notes="补单")
    @Override
    public BaseResponse<OrderVO> additionalOrder(@RequestParam("mainOrderNo") String mainOrderNo,
                                                 @RequestParam("num") Double num,
                                                 @RequestParam("serviceAccount")String serviceAccount,
                                                 @RequestParam("addOrderType")Integer addOrderType) {
        BaseResponse<OrderVO> response = new BaseResponse();
        if (num == null) {
            throw new OrderException("补单数量不能为空");
        }
        if (addOrderType == null) {
            throw new OrderException("补单参数不能为空");
        }
        orderService.additionalOrder(mainOrderNo,num,serviceAccount,addOrderType);
        return response;
    }
}
