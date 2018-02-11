package com.qibao.frontend.feign;

import com.qibao.activity.entity.service.IUserPrizeControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IUserPrizeFeign extends IUserPrizeControl {

}
