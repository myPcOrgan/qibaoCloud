package com.qibao.goods.redis.impl;

import com.qibao.common.redis.AbstractRedisDAO;
import com.qibao.goods.redis.IGoodsRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 周黎钢 on 2018/1/10.
 */
@Component
public class GoodsRedisImpl extends AbstractRedisDAO implements IGoodsRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private final static String GOODS_ID_SEQ="QB:GOODS:ID:";
    @Override
    protected StringRedisTemplate getTemplate() {
        return stringRedisTemplate;
    }
    @Override
    public void saveGoodsInfo() {
        valueOps.set("name","zhouligang");

    }

    @Override
    public String getGoodsInfo() {
        return valueOps.get("name");
    }

    @Override
    public void deleteGoodsInfo() {
        stringRedisTemplate.delete("name");
    }
    /**
     * 生成商品单号
     *
     * @param prefix
     * @return
     */
    @Override
    public String goodsId(String prefix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        Date now = new Date();
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(now);
        long id = valueOps.increment(GOODS_ID_SEQ+prefix, 1);
        if (id == 1L) {
            // 设置缓存数据最后的失效时间为当天的最后一秒
            nowCal = Calendar.getInstance();
            nowCal.setTime(now);
            Calendar lastCal = Calendar.getInstance();
            lastCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH),
                    nowCal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
            lastCal.set(Calendar.MILLISECOND, 999);
            stringRedisTemplate.expireAt(GOODS_ID_SEQ+prefix,lastCal.getTime());
        }
        String orderId = StringUtils.leftPad(String.valueOf(id), 9, '0');
        StringBuffer sb = new StringBuffer(prefix);
        sb.append(sdf.format(nowCal.getTime())).append(orderId);
        return sb.toString();
    }
}
