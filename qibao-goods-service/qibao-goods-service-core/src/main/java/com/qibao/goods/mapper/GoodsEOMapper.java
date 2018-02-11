package com.qibao.goods.mapper;

import com.qibao.common.mapper.IBaseMapper;
import com.qibao.goods.entity.GoodsEO;

import java.util.List;
import java.util.Map;

public interface GoodsEOMapper extends IBaseMapper<GoodsEO> {
    List<GoodsEO> selectByMap(Map map);

    void batchUpdateGoods(List<GoodsEO> list);
}