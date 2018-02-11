package com.qibao.payment.feign;

import com.qibao.activity.entity.service.IUserPrizeControl;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by ljn on 2018/2/2.
 */
@FeignClient("qibao-activity-service")
public interface IUserPrizeFeign extends IUserPrizeControl {

}
