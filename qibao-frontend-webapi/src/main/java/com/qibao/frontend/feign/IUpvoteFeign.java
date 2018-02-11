package com.qibao.frontend.feign;

import com.qibao.activity.entity.service.IUpvoteControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IUpvoteFeign extends IUpvoteControl {
}
