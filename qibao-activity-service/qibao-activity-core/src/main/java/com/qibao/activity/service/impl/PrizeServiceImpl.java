package com.qibao.activity.service.impl;

import com.qibao.activity.entity.PrizeEO;
import com.qibao.activity.mapper.PrizeEOMapper;
import com.qibao.activity.service.IPrizeService;
import com.qibao.common.service.abs.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrizeServiceImpl extends BaseService<PrizeEO> implements IPrizeService {

    @Autowired
    private PrizeEOMapper prizeEOMapper;

    @Override
    public List<PrizeEO> selectPrizeList(PrizeEO prizeEO) {
        List<PrizeEO> prizeEOS=prizeEOMapper.select(prizeEO);
        return prizeEOS;
    }

}
