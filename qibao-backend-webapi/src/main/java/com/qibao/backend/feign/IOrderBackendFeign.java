package com.qibao.backend.feign;

import com.qibao.order.service.IOrderController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/1/25.
 */
@FeignClient(value = "qibao-order-service")
public interface IOrderBackendFeign extends IOrderController{

}
