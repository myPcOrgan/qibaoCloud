package com.qibao.frontend.feign;

import com.qibao.user.context.service.IThirdLoginController;
import org.springframework.cloud.netflix.feign.FeignClient;


@FeignClient(value = "qibao-user-service")
public interface IUserLoginFeign extends IThirdLoginController {

}
