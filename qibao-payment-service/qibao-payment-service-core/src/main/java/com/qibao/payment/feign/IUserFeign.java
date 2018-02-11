package com.qibao.payment.feign;

import com.qibao.user.context.service.IUserController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/2/2.
 */
@FeignClient("qibao-user-service")
public interface IUserFeign extends IUserController{

}
