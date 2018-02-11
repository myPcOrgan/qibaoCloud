package com.qibao.backend.feign;

import com.qibao.user.context.service.IMessageController;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "qibao-user-service")
public interface ISysMessageFeign extends IMessageController {

}
