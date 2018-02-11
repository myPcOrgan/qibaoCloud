package com.qibao.activity.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 房间开奖任务
 */
@Component
public class ActivityLottyJob {

    public static final Logger LOGGER = LoggerFactory.getLogger(ActivityLottyJob.class);

    /**
     * 倒计时开奖
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void Lotty(){   //TODO 这个要加锁

    }

    /**
     * 活动用户金币返回
     */
    /*@Scheduled
    public void activityUserGoldUpdate(){

    }*/
}
