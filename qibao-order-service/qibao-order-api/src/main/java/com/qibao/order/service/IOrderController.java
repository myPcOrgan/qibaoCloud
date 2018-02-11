package com.qibao.order.service;

import com.qibao.common.dto.BaseResponse;
import com.qibao.order.content.OrderDTO;
import com.qibao.order.vo.OrderVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

/**
 * Created by ljn on 2018/1/10.
 */
@RequestMapping("order")
public interface IOrderController  {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @RequestMapping(value="createOrder",method = RequestMethod.POST)
    public BaseResponse<OrderVO> createOrder(@RequestBody OrderDTO orderDTO);

    /**
     * 查询订单列表
     * @return
     */
    @RequestMapping(value="queryOrderList",method = RequestMethod.POST)
    public BaseResponse<OrderVO> queryOrderList(@RequestBody OrderDTO orderDTO) throws ParseException;

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    @RequestMapping(value="queryOrderByOrderNo",method = RequestMethod.GET)
    public BaseResponse<OrderVO> queryOrderByOrderNo(@RequestParam(name="orderNo",required = true) String orderNo);

    /**
     * 取消订单
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "cancelOrder",method = RequestMethod.GET)
    public BaseResponse<OrderVO> cancelOrder(@RequestParam(name="orderNo",required = true) String orderNo);

    /**
     * 交易成功(商城购买，支付回调)
     * @param orderNo
     * @return
     */
    @RequestMapping(value="tradeSuccess",method = RequestMethod.GET)
    public BaseResponse<OrderVO> tradeSuccess(@RequestParam(name="orderNo",required = true) String orderNo);

    /**
     * 金币商城分配角色回调
     * @param orderNo
     * @return
     */
    @RequestMapping(value="mallAssignRole",method = RequestMethod.GET)
    public BaseResponse<OrderVO> mallAssignRole(@RequestParam(name="orderNo",required = true)String orderNo);

    /**
     * 金币商城交易成功回调
     * @param orderNo
     * @param dealNum
     * @return
     */
    @RequestMapping(value="mallTradeSuccess",method = RequestMethod.GET)
    public BaseResponse<OrderVO> mallTradeSuccess(@RequestParam("orderNo")String orderNo,@RequestParam("dealNum")Double dealNum);

    /**
     * 平台用户购买的金币总数
     * @return
     */
    @RequestMapping(value="selectBuyGoldNum",method = RequestMethod.GET)
    public BaseResponse<Double> selectBuyGoldNum(@RequestParam("userType")Integer userType,
                                                 @RequestParam("startTime")String startTime,
                                                 @RequestParam("endTime")String endTime) throws ParseException;

    @RequestMapping(value="selectMallTradeGold",method = RequestMethod.GET)
    public BaseResponse<Double> selectMallTradeGold(@RequestParam("orderType")Integer orderType,
                                                    @RequestParam("startTime")String startTime,
                                                    @RequestParam("endTime")String endTime) throws ParseException;

    @RequestMapping(value="selectPoundage",method = RequestMethod.GET)
    public BaseResponse<Double> selectPoundage(@RequestParam("startTime")String startTime,
                                               @RequestParam("endTime")String endTime) throws ParseException;

    @RequestMapping(value="additionalOrder",method = RequestMethod.GET)
    public BaseResponse<OrderVO> additionalOrder(@RequestParam("mainOrderNo") String mainOrderNo,
                                                 @RequestParam("num") Double num,
                                                 @RequestParam("serviceAccount")String serviceAccount,
                                                 @RequestParam("addOrderType")Integer addOrderType);

}
