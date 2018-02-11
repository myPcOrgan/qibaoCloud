package com.qibao.frontend.api.goods.controller;

import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.api.goods.service.IGoodsFeign;
import com.qibao.goods.model.GoodsRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 周黎钢 on 2018/1/18.
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private IGoodsFeign feign;
    @RequestMapping(value = "/getGoodsInfos",method = RequestMethod.POST)
    @ApiOperation(value = "查询商品信息", notes="查询商品信息，参数为id")
    public BaseResponse getGoodsInfos(@RequestBody GoodsRequest goodsRequest){
        return feign.getGoodsInfos(goodsRequest);
    }
}
