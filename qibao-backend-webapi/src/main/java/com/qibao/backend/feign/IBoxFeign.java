package com.qibao.backend.feign;

import com.qibao.activity.entity.service.IBoxControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IBoxFeign extends IBoxControl {

}
