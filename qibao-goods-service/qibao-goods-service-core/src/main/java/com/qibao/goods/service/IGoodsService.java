package com.qibao.goods.service;

import com.qibao.common.service.IBaseService;
import com.qibao.goods.entity.GoodsEO;
import com.qibao.goods.model.GoodsRequest;

import java.util.List;
public interface IGoodsService extends IBaseService<GoodsEO> {
    void insertGoods(GoodsRequest goodsRequest);

    void updateGoods(GoodsRequest goodsRequest);

    void batchUpdate(List<GoodsRequest> goodsRequests);

    GoodsEO getGoodsInfo(Long id);

    List<GoodsEO> getGoodsInfos(GoodsRequest goodsRequest);
}
