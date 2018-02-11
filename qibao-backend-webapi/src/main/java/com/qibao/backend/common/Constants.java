package com.qibao.backend.common;

/**
 * Created by 周黎钢 on 2018/1/22.
 */
public class Constants {
    /**
     * 用户自定义session
     */
    public static final String COOKIE_NAME = "_backCookie";

    /**
     * 用户账号-请求认证HTTP HEAD标签
     */
    public static final String SERVICE_REQUEST_HEADER_ACCOUNT = "userAccount";

    /**
     * 用户authkey-请求认证HTTP HEAD标签,仅在有更高权限验证需求下，每次请求都会带上用户ID或者账号时使用
     */
    public static final String SERVICE_REQUEST_HEADER_AUTHKEY = "authkey";

    /**
     * ip自定义SessionId
     */
    public static final String PARAMS_SESSIONID = "_SRHSI";

    public static final String HEADER_IP_KEY = ".client_ip";

}
