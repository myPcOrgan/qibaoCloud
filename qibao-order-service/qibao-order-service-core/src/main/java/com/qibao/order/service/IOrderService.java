package com.qibao.order.service;

import com.qibao.common.service.IBaseService;
import com.qibao.order.entity.OrderEO;
import com.qibao.order.vo.OrderVO;

import java.util.List;
import java.util.Map;

/**
 * Created by ljn on 2018/1/10.
 */
public interface IOrderService  extends IBaseService<OrderEO> {

    void createOrder(OrderEO orderEO);

    void additionalOrder(String mainOrderNo,Double num,String serviceAccount,Integer addOrderType);

    List<OrderVO> queryOrderList(Map<String,Object> params, Integer page, Integer size, String orderBy, boolean isAsc);

    int countByMap(Map<String,Object> params);

    OrderEO queryOrderByOrderId(String orderNo);

    void cancelOrder(String orderNo);

    OrderVO tradeSuccess(String orderNo);

    OrderVO mallAssignRole(String orderNo);

    OrderVO mallTradeSuccess(String orderNo,Double dealNum);

    double selectBuyGoldNum(Map<String,Object> params);

    double selectMallTradeGold(Map<String,Object> params);

    double selectPoundage(Map<String,Object> params);
}
