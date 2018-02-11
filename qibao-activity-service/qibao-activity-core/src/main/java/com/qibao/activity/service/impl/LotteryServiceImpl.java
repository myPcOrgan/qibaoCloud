package com.qibao.activity.service.impl;

import com.qibao.activity.entity.PrizeEO;
import com.qibao.activity.mapper.PrizeEOMapper;
import com.qibao.activity.service.ILotteryService;
import com.qibao.common.service.abs.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class LotteryServiceImpl extends BaseService<PrizeEO> implements ILotteryService {

    @Autowired
    private PrizeEOMapper prizeEOMapper;

    @Override
    public PrizeEO getLottery(List<PrizeEO> prizeList) {
        if (CollectionUtils.isEmpty(prizeList)) {
            return null;
        } else {
            int random = -1;
            double sumProbability = 0;
            for (PrizeEO p : prizeList) {
                sumProbability += p.getPrizeProbability();
            }
            if (sumProbability == 0) {
                return null;
            }
            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < prizeList.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(prizeList.get(i).getPrizeProbability())) / sumProbability;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(prizeList.get(i - 1).getPrizeProbability())) / sumProbability;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;
                    break;
                }
            }
            return prizeList.get(random);
        }
    }

}
