package com.qibao.backend.feign;


import com.qibao.backend.controller.IBackendLogController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by 340067 on 2018/1/25.
 */
@FeignClient(value = "qibao-backend-service")
public interface IBackendLogFeign extends IBackendLogController {

}
