package com.qibao.goods.service.impl;

import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.goods.entity.GoodsEO;
import com.qibao.goods.exception.GoodsException;
import com.qibao.goods.mapper.GoodsEOMapper;
import com.qibao.goods.model.GoodsRequest;
import com.qibao.goods.redis.IGoodsRedis;
import com.qibao.goods.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends BaseService<GoodsEO> implements IGoodsService {
    @Autowired
    private IGoodsRedis goodsRedis;
    @Autowired
    private GoodsEOMapper goodsEOMapper;

    @Override
    public void insertGoods(GoodsRequest goodsRequest) {
        if (StringUtils.isBlank(goodsRequest.getGoodsName())) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.GOODS_NAME_NOT_NULL);
        }
        if (StringUtils.isBlank(goodsRequest.getGoodsUnit())) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.GOODS_UNIT_NOT_NULL);
        }
        if (goodsRequest.getGoodsNum() == null) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.GOODS_NUM_NOT_NULL);
        }
        if (goodsRequest.getGoodsAmount() == null) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.GOODS_AMOUNT_NOT_NULL);
        }
        if (goodsRequest.getCategory() == null) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.CATE_CODE_NOT_NULL);
        }
        GoodsEO goodsEO = new GoodsEO();
        BeanMapper.copyPropertiesIgnoreNull(goodsRequest, goodsEO);
        goodsEO.setGoodsNo(goodsRedis.goodsId("QB"));
        goodsEO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        goodsEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        goodsEO.setIsDeleted(false);
        this.insert(goodsEO);
    }


    @Override
    public void updateGoods(GoodsRequest goodsRequest) {
        if (goodsRequest.getId() == null) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.ID_NOT_NULL);
        }
        if (goodsRequest.getIsDeleted() != null && !goodsRequest.getIsDeleted()) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.IS_DELETED);
        }
        GoodsEO goodsEO = new GoodsEO();
        BeanMapper.copyPropertiesIgnoreNull(goodsRequest, goodsEO);
        goodsEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.update(goodsEO);
    }

    @Override
    public void batchUpdate(List<GoodsRequest> goodsRequests) {
        List<GoodsEO> goodsEOs = BeanMapper.mapList(goodsRequests, GoodsEO.class);
        for (GoodsEO goodsEO : goodsEOs) {
            if (goodsEO.getId() == null) {
                throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.ID_NOT_NULL);
            }
            if (goodsEO.getIsDeleted() != null && !goodsEO.getIsDeleted()) {
                throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.IS_DELETED);
            }
            goodsEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        }
        goodsEOMapper.batchUpdateGoods(goodsEOs);
    }

    @Override
    public GoodsEO getGoodsInfo(Long id) {
        GoodsEO goodsEO = this.selectById(id);
        if (goodsEO.getIsDeleted()) {
            return null;
        }
        return goodsEO;
    }

    @Override
    public List<GoodsEO> getGoodsInfos(GoodsRequest goodsRequest) {
        //用自定义的分页查询方法，提高效率
        Map map = BeanMapper.map(goodsRequest, Map.class);
        map.put("ORDERBY", "create_time");
        map.put("ORDER", "DESC");
        if (goodsRequest.getPage() != null && goodsRequest.getPageSize() != null) {
            Integer start = (goodsRequest.getPage() - 1) * goodsRequest.getPageSize();
            map.put("limit", goodsRequest.getPageSize());
            map.put("start", start);
        }
        if (StringUtils.isNotBlank(goodsRequest.getOrderBy())) {
            map.put("ORDERBY", goodsRequest.getOrderBy());
        }
        if (goodsRequest.getIsAsc() != null && goodsRequest.getIsAsc()) {
            map.put("ORDER", "ASC");
        }
        List<GoodsEO> goodsEOs = goodsEOMapper.selectByMap(map);
        return goodsEOs;
    }
}
