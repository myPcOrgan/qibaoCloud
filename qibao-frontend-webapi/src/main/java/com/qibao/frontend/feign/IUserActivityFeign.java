package com.qibao.frontend.feign;

import com.qibao.activity.entity.service.IUserActivityControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IUserActivityFeign extends IUserActivityControl {
}
