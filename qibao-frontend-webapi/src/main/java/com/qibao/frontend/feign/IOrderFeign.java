package com.qibao.frontend.feign;

import com.qibao.order.service.IOrderController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/1/12.
 */
@FeignClient(value = "qibao-order-service")
public interface IOrderFeign extends IOrderController{
}
