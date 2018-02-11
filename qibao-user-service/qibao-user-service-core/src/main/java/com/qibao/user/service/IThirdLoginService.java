package com.qibao.user.service;


import com.qibao.user.entity.UserAccountEO;

/**
 * Created by 340067 on 2018/1/11.
 */
public interface IThirdLoginService {




    /**
     * 保存手机登录验证码方法
     */
    void addPhoneVerifyCode(String phone, String context);

    /**
     * 验证验证码是否正确,如果正确返回用户信息，反之返回空
     * @param phone
     * @param code
     * @return
     */
    UserAccountEO checkPhoneVerifyCode(String phone, String code, String userIp);

}
