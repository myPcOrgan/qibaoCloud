package com.qibao.goods.redis;


/**
 * Created by 周黎钢 on 2018/1/10.
 */
public interface IGoodsRedis {
    void saveGoodsInfo();

    String getGoodsInfo();

    void deleteGoodsInfo();

    String goodsId(String prefix);

}
