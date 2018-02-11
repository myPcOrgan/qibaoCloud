package com.qibao.frontend.feign;

import com.qibao.activity.entity.service.ILotteryControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface ILotteryFeign extends ILotteryControl {

}
