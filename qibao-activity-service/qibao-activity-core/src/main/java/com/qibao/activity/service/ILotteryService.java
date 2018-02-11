package com.qibao.activity.service;

import com.qibao.activity.entity.PrizeEO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface ILotteryService extends IBaseService<PrizeEO> {

    PrizeEO getLottery(List<PrizeEO> prizeList);
}
