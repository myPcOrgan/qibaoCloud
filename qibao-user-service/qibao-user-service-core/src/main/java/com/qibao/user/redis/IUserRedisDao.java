package com.qibao.user.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by 340067 on 2018/1/12.
 */
public interface IUserRedisDao {

    void addValueRedis(String key, String value);

    String getValueRedis(String key);

    void addLockValueRedis(String lockKey, String value, long timeout, TimeUnit timeUnit);

    Long addIncrement(String key);

    void setExpireAt(String key, Date date);

    void delKey(String key);

}
