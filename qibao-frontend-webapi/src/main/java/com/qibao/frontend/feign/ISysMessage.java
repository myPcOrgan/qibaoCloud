package com.qibao.frontend.feign;

import com.qibao.user.context.service.IMessageController;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "qibao-user-service")
public interface ISysMessage extends IMessageController {

}
