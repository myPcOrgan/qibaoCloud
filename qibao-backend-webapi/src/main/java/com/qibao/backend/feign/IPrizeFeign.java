package com.qibao.backend.feign;

import com.qibao.activity.entity.service.IPrizeControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IPrizeFeign extends IPrizeControl {

}
