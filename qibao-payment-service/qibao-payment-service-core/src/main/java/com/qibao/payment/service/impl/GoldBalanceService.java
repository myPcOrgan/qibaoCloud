package com.qibao.payment.service.impl;

import com.github.pagehelper.PageHelper;
import com.qibao.common.utils.BeanMapper;
import com.qibao.finance.vo.GoldBalanceVO;
import com.qibao.payment.entity.GoldBalanceEO;
import com.qibao.payment.mapper.GoldBalanceEOMapper;
import com.qibao.payment.service.IGoldBalanceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ljn on 2018/2/2.
 */
@Service
public class GoldBalanceService implements IGoldBalanceService{

    @Autowired
    private GoldBalanceEOMapper goldBalanceEOMapper;


    @Override
    public void add(GoldBalanceEO goldBalanceEO) {
        goldBalanceEOMapper.insert(goldBalanceEO);
    }


    @Override
    public List<GoldBalanceVO> selectGoldBalance(Map<String,Object> map, Integer page, Integer size, String orderBy, boolean isAsc) {
        if (page != null && size != null) {
            PageHelper.startPage(page,size);
        }
        if (StringUtils.isNotBlank(orderBy)) {
            map.put("ORDERBY",orderBy);
        }
        if (isAsc) {
            map.put("ORDER","ASC");
        }else{
            map.put("ORDER","DESC");
        }
        List<GoldBalanceEO> goldBalanceEOs = goldBalanceEOMapper.selectGoldBalance(map);
        List<GoldBalanceVO> list = BeanMapper.mapList(goldBalanceEOs, GoldBalanceVO.class);
        return list;

    }

    @Override
    public Integer countByMap(Map<String, Object> map) {
        return goldBalanceEOMapper.countByMap(map);
    }
}
