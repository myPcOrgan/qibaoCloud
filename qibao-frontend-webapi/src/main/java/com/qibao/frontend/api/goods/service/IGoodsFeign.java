package com.qibao.frontend.api.goods.service;

import com.qibao.goods.controller.IGoodsController;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by 周黎钢 on 2018/1/17.
 */
@FeignClient(value = "qibao-goods-service")
public interface IGoodsFeign extends IGoodsController{
}
