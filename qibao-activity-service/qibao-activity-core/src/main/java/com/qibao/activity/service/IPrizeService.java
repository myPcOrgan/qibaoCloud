package com.qibao.activity.service;

import com.qibao.activity.entity.PrizeEO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IPrizeService extends IBaseService<PrizeEO> {

    List<PrizeEO> selectPrizeList(PrizeEO prizeEO);
}
