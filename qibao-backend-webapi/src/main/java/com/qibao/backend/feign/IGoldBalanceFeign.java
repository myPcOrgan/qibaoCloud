package com.qibao.backend.feign;

import com.qibao.finance.service.IGoldBalanceController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/2/6.
 */
@FeignClient(value = "qibao-payment-service")
public interface IGoldBalanceFeign extends IGoldBalanceController {
}
