package com.qibao.frontend.feign;

import com.qibao.activity.entity.service.IRoomActivityControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IActivityFeign extends IRoomActivityControl {
}
