package com.qibao.order.mapper;

import com.qibao.common.mapper.IBaseMapper;
import com.qibao.order.entity.OrderEO;

import java.util.List;
import java.util.Map;

public interface OrderEOMapper extends IBaseMapper<OrderEO> {

    List<OrderEO> getOrderList(Map<String,Object> map);

    int countByMap(Map<String,Object> map);

    OrderEO selectOrderForUpdate(String orderNo);

    /**
     * 平台用户购买的金币总数
     * @return
     */
    double selectBuyGoldNum(Map<String,Object> map);

    /**
     * 查询金币商城交易金币(包括存入，取回)
     * @param map
     * @return
     */
    double selectMallTradeGold(Map<String,Object> map);

    double selectPoundage(Map<String,Object> map);
}