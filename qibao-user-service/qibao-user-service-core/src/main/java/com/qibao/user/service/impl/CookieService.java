package com.qibao.user.service.impl;


import com.qibao.user.redis.IUserRedisDao;
import com.qibao.user.service.ICookieService;
import com.qibao.user.utils.UserRedisKeyHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CookieService implements ICookieService{


    @Autowired
    IUserRedisDao userRedisDao;

    @Override
    public String createSession(Long userId) {
        String authkey = UUID.randomUUID().toString().replaceAll("-", "");
        //判断原有session，去重
        String valueRedis = userRedisDao.getValueRedis(UserRedisKeyHelper.userSessionCreate(userId.toString()));
        if (StringUtils.isNotBlank(valueRedis)){
            String valueRedis1 = userRedisDao.getValueRedis(UserRedisKeyHelper.userSessionCreateKey(valueRedis));
            if (StringUtils.isNotBlank(valueRedis1)){
                userRedisDao.delKey(UserRedisKeyHelper.userSessionCreateKey(valueRedis));
            }
            userRedisDao.delKey(UserRedisKeyHelper.userSessionCreate(userId.toString()));
        }
        userRedisDao.addValueRedis(UserRedisKeyHelper.userSessionCreate(userId.toString()),authkey);
         userRedisDao.addValueRedis(UserRedisKeyHelper.userSessionCreateKey(authkey),userId.toString());
         return authkey;
    }

    @Override
    public void delUserSession(Long userId) {
        String authkey = userRedisDao.getValueRedis(UserRedisKeyHelper.userSessionCreate(userId.toString()));
        if (StringUtils.isNotBlank(authkey)){
            userRedisDao.delKey(UserRedisKeyHelper.userSessionCreateKey(authkey));
            userRedisDao.delKey(UserRedisKeyHelper.userSessionCreate(userId.toString()));
        }

    }

    @Override
    public String createIpSession(String ip) {
        String authkey = UUID.randomUUID().toString().replaceAll("-", "");
        String auValueRedis = userRedisDao.getValueRedis(UserRedisKeyHelper.ip2SessionId(ip));
        if (StringUtils.isNotBlank(auValueRedis)){
            String ipValueRedis = userRedisDao.getValueRedis(UserRedisKeyHelper.sessionId2Ip(auValueRedis));
            if (StringUtils.isNotBlank(ipValueRedis)){
                userRedisDao.delKey(UserRedisKeyHelper.sessionId2Ip(auValueRedis));
            }
            userRedisDao.delKey(UserRedisKeyHelper.ip2SessionId(ip));
        }
        userRedisDao.addLockValueRedis(UserRedisKeyHelper.sessionId2Ip(authkey), ip, 60, TimeUnit.MINUTES);
        userRedisDao.addLockValueRedis(UserRedisKeyHelper.ip2SessionId(ip),authkey,60, TimeUnit.MINUTES);
        return authkey;
    }
}
