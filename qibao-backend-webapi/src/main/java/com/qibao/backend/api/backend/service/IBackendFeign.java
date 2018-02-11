package com.qibao.backend.api.backend.service;

import com.qibao.backend.controller.IBackendController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by 周黎钢 on 2018/1/24.
 */
@FeignClient(value = "qibao-backend-service")
public interface IBackendFeign extends IBackendController{
//    @RequestMapping(value = "backService/login", method = RequestMethod.POST)
//    BaseResponse login(@RequestBody BackendRequest request, HttpServletRequest httpRequest);
}
