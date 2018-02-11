package com.qibao.order.redis.impl;

/**
 * Created by 339939 on 2018/1/10.
 */
public class OrderRedisKeyHelper {

    private static final String namespace = "qibao:order";

    public static String createOrderId() {
        return namespace + ":createOrderId:"  ;
    }
}
