package com.qibao.payment.service;

import com.qibao.finance.vo.GoldBalanceVO;
import com.qibao.payment.entity.GoldBalanceEO;

import java.util.List;
import java.util.Map;

/**
 * Created by ljn on 2018/2/2.
 */
public interface IGoldBalanceService {

    void add(GoldBalanceEO goldBalanceEO);

    List<GoldBalanceVO> selectGoldBalance(Map<String,Object> map, Integer page, Integer size, String orderBy, boolean isAsc);

    Integer countByMap(Map<String,Object> map);
}
