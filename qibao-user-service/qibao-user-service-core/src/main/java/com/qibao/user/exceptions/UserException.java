package com.qibao.user.exceptions;

import com.qibao.common.exception.BaseException;

/**
 * Created by wuyb on 2018/1/19.
 */
public class UserException extends BaseException{

    private static final Integer errorCode = 1;

    public static final String PARAM_EMPTY = "参数不能为空";

    public static final String USERID_EMPTY = "用户id不能为空";

    public static final String PHONE_ERROR_FORMAT = "手机号格式错误";

    public static final String PHONE_EMPTY = "手机号不能为空";

    public static final String IP_EMPTY = "用户ip不能为空";

    public static final String USER_NOT_EXITED = "该用户不存在";




    public UserException(String errorMsg) {
        super(errorCode, errorMsg);
    }
}
