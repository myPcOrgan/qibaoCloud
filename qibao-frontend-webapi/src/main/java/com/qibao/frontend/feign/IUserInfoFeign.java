package com.qibao.frontend.feign;

import com.qibao.user.context.service.IUserController;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "qibao-user-service")
public interface IUserInfoFeign extends IUserController {


}
