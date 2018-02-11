package com.qibao.backend.feign;

import com.qibao.activity.entity.service.IImageControl;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("qibao-activity-service")
public interface IImageFeign extends IImageControl {

}
