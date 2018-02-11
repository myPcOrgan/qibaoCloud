package com.qibao.common.dto;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.util.Map;

/**
 * @author Link
 */
public interface IResultMap extends Map<String, Object>, Cloneable, Serializable, InvocationHandler {
    /**
     * 成功
     */
    void success();

    /**
     * 成功(消息)
     *
     * @param msg
     */
    void success(String msg);

    /**
     * 失败
     */
    void fail();

    /**
     * 失败(消息)
     *
     * @param msg
     */
    void fail(String msg);

    /**
     * 错误
     */
    void error();

    /**
     * 错误(消息)
     *
     * @param msg
     */
    void error(String msg);
}
