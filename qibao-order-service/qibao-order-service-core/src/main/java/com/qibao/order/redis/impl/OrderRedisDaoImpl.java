package com.qibao.order.redis.impl;

import com.qibao.common.redis.AbstractRedisDAO;
import com.qibao.order.redis.IOrderRedisDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ljn on 2018/1/10.
 */
@Repository
public class OrderRedisDaoImpl extends AbstractRedisDAO implements IOrderRedisDao{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected StringRedisTemplate getTemplate() {
        return redisTemplate;
    }

    @Override
    public String getOrderId(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        Date now = new Date();
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(now);
        long id = valueOps.increment(OrderRedisKeyHelper.createOrderId(), 1);
        if (id == 1L) {
            //设置缓存数据最后的失效时间为当天的最后一秒
            nowCal = Calendar.getInstance();
            nowCal.setTime(now);
            Calendar lastCal = Calendar.getInstance();
            lastCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
            lastCal.set(Calendar.MILLISECOND, 999);
            redisTemplate.expireAt(OrderRedisKeyHelper.createOrderId() + prefix, lastCal.getTime());
        }
        return prefix + sdf.format(nowCal.getTime()) + StringUtils.leftPad(String.valueOf(id), 7, '0');
    }
}
