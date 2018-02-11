package com.qibao.user.redis.impl;

import com.qibao.common.redis.AbstractRedisDAO;
import com.qibao.user.redis.IUserRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 340067 on 2018/1/11.
 */
@Component
public class UserRedisDao extends AbstractRedisDAO implements IUserRedisDao{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected StringRedisTemplate getTemplate() {
        return redisTemplate;
    }

    @Override
    public void addValueRedis(String key, String value) {
        valueOps.set(key,value);
    }

    @Override
    public String getValueRedis(String key) {
        return valueOps.get(key);
    }

    @Override
    public void addLockValueRedis(String lockKey, String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(lockKey,value,timeout,timeUnit);
    }

    @Override
    public Long addIncrement(String key) {
        Long num = redisTemplate.opsForValue().increment(key, 1);
        return num;
    }

    @Override
    public void setExpireAt(String key, Date date) {
        redisTemplate.expireAt(key,date);
    }

    @Override
    public void delKey(String key) {
        redisTemplate.delete(key);
    }
}
