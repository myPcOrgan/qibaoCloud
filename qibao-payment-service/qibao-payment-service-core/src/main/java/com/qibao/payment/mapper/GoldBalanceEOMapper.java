package com.qibao.payment.mapper;

import com.qibao.common.mapper.IBaseMapper;
import com.qibao.payment.entity.GoldBalanceEO;

import java.util.List;
import java.util.Map;

public interface GoldBalanceEOMapper extends IBaseMapper<GoldBalanceEO> {

    List<GoldBalanceEO> selectGoldBalance(Map<String,Object> map);

    Integer countByMap(Map<String,Object> map);
}