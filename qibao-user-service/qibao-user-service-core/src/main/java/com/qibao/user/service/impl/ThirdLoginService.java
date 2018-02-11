package com.qibao.user.service.impl;

import com.qibao.common.exception.BaseException;
import com.qibao.common.service.abs.BaseService;
import com.qibao.user.context.enums.SysMessageTypeEnum;
import com.qibao.user.context.enums.UserGradeEnum;
import com.qibao.user.entity.MessageEO;
import com.qibao.user.entity.UserAccountEO;
import com.qibao.user.enums.ThirdLoginTypeEnum;
import com.qibao.user.redis.IUserRedisDao;
import com.qibao.user.service.IMessageService;
import com.qibao.user.service.IThirdLoginService;
import com.qibao.user.service.IUserService;
import com.qibao.user.utils.UserRedisKeyHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 340067 on 2018/1/11.
 */
@Component
public class ThirdLoginService extends BaseService<MessageEO> implements IThirdLoginService {


    @Autowired
    IUserService userService;
    @Autowired
    IUserRedisDao userRedisDao;
    @Autowired
    IMessageService messageService;

    @Override
    public void addPhoneVerifyCode(String phone, String code) {
        try {
            MessageEO messageEO = new MessageEO();
            messageEO.setCreateTime(new Date());
            messageEO.setContent("手机号" + phone + ";验证码" + code);
            messageEO.setIsView(true);
            messageEO.setMessageType(SysMessageTypeEnum.LOGIN.getCode());
            messageEO.setName("登录");
            messageService.insert(messageEO);
        } catch (Exception e) {
            LOGGER.error("短信消息插入数据库发生异常");
        }
        userRedisDao.addLockValueRedis(UserRedisKeyHelper.userPhoneVerifyCodeRedis(phone),code,60, TimeUnit.SECONDS);
    }

    @Override
    public UserAccountEO checkPhoneVerifyCode(String phone, String code, String userIp) {
        String code1 = userRedisDao.getValueRedis(UserRedisKeyHelper.userPhoneVerifyCodeRedis(phone));
        LOGGER.info("短信验证码：" + code1);
        if (StringUtils.isNotBlank(code1)){
//            if (code.equals(code1)){
                UserAccountEO userAccountEO = userService.checkQQExist(phone, ThirdLoginTypeEnum.PHONE.getCode());
                if (userAccountEO == null){
                    userAccountEO = new UserAccountEO();
                    userAccountEO.setMobilePhone(phone);
                    userAccountEO.setLoginType(ThirdLoginTypeEnum.PHONE.getCode());
                    userAccountEO.setRegisterIp(userIp);
                    userAccountEO.setLastLoginTime(new Date());
                    userAccountEO.setUserGrade(UserGradeEnum.GENERAL.getCode());
                    userService.insertUserInfo(userAccountEO);
                }
                if (userAccountEO.getIsDeleted() != null && userAccountEO.getIsDeleted()){
                    throw new BaseException(01,"该用户不存在");
                }
                if (userAccountEO.getIsForbid() != null && userAccountEO.getIsForbid()){
                    throw new BaseException(01,"该用户已被禁止");
                }
                return userAccountEO;
//            } else{
//              return null;
//            }
        } else {
            throw new BaseException(01,"验证码过期");
        }
    }
}
