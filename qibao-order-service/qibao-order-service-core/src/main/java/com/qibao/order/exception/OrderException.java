package com.qibao.order.exception;

import com.qibao.common.exception.BaseException;

/**
 * Created by ljn on 2018/1/19.
 */
public class OrderException extends BaseException{

    private static final Integer errorCode = 1;

    public static final String ORDER_IS_EXIT = "该订单不存在";

    public static final String ORDER_IS_END = "该订单已完结";

    public static final String PARAM_IS_ERROR = "参数错误";

    public static final String USER_ACCOUNT_IS_EMPTY = "用户账号为空";



    public OrderException(String errorMsg) {
        super(errorCode, errorMsg);
    }
}
