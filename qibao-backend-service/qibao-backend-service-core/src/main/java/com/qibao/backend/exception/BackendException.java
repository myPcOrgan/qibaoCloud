package com.qibao.backend.exception;

import com.qibao.common.exception.BaseException;

/**
 * Created by 周黎钢 on 2018/1/11.
 */
public class BackendException extends BaseException {
    //普通异常
    public final static Integer COMMON_EXCEPTION =1;
    //严重异常
    public final static Integer FATAL_EXCEPTION =2;
    public final static String ID_NOT_NULL ="ID不能为空";
    public final static String USER_ID_NOT_NULL ="用户ID不能为空";
    public final static String ROLE_ID_NOT_NULL ="角色ID不能为空";
    public final static String LOGIN_ACCOUNT_NOT_NULL ="登录帐号不能为空";
    public final static String PASSWORD_NOT_NULL ="登录密码不能为空";
    public final static String REAL_NAME_NOT_NULL ="姓名不能为空";
    public final static String PHONE_NUMBER_NOT_NULL ="手机号不能为空";
    public final static String LOGIN_FAILED ="登录失败,请检查账号或密码";
    public final static String SERVICE_QQ_NOT_NULL ="客服QQ不能为空";
    public final static String SERVICE_NAME_NOT_NULL ="客服名字不能为空";
    public final static String IS_SERVICE_NOT_NULL ="是否客服字段不能为空";
    public final static String ROLE_NAME_NOT_NULL ="角色名字不能为空";
    public final static String ROLE_DESC_NOT_NULL ="角色描述不能为空";
    public final static String ROLE_IDS_NOT_NULL ="角色ID集合不能为空";
    public final static String FUNCTION_ID_NOT_NULL ="权限ID不能为空";
    public final static String FUNCTION_IDS_NOT_NULL ="权限ID集合不能为空";
    public final static String FUNCTION_NAME_NOT_NULL ="权限名字不能为空";
    public final static String FUNCTION_DESC_NOT_NULL ="权限描述不能为空";
    public final static String URI_NOT_NULL ="uri不能为空";
    public final static String PARENT_ID_NOT_NULL ="权限父类ID不能为空";
    //该用户已经被删除
    public final static String IS_DELETED ="非法操作，无法执行";
    public BackendException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
