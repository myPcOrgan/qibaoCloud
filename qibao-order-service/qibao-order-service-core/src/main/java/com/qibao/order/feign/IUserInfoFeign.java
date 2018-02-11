package com.qibao.order.feign;

import com.qibao.user.context.service.IUserController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/2/9.
 */
@FeignClient(value = "qibao-user-service")
public interface IUserInfoFeign extends IUserController {
}
