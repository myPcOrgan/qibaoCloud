package com.qibao.order.redis;

/**
 * Created by 339939 on 2018/1/10.
 */
public interface IOrderRedisDao {

    String getOrderId(String prefix);
}
