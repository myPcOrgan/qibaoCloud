package com.qibao.payment.feign;

import com.qibao.order.service.IOrderController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/2/2.
 */
@FeignClient("qibao-order-service")
public interface IOrderFeign extends IOrderController{


}
