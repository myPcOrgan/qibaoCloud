package com.qibao.backend.api.goods.controller;

import com.qibao.backend.api.goods.service.IGoodsFeign;
import com.qibao.common.dto.BaseResponse;
import com.qibao.goods.model.GoodsRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/18.
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private IGoodsFeign feign;
    @RequestMapping(value = "insertGoods", method = RequestMethod.POST)
    @ApiOperation(value = "增加商品信息", notes="增加商品信息，参数为GoodsDTO")
    BaseResponse insertGoods(@RequestBody GoodsRequest goodsRequest){
        return feign.insertGoods(goodsRequest);
    }
    @RequestMapping(value = "batchUpdateGoods", method = RequestMethod.POST)
    @ApiOperation(value = "批量修改商品信息", notes="批量修改商品信息，参数为List<GoodsRequest>")
    BaseResponse batchUpdateGoods(@RequestBody List<GoodsRequest> goodsRequests){
        return feign.batchUpdateGoods(goodsRequests);
    }
    @RequestMapping(value = "updateGoods", method = RequestMethod.POST)
    @ApiOperation(value = "修改单个商品信息", notes="修改单个商品信息，参数为GoodsDTO")
    BaseResponse updateGoods(@RequestBody GoodsRequest goodsRequest){
        return feign.updateGoods(goodsRequest);
    }
    @RequestMapping(value = "getGoodsInfos", method = RequestMethod.POST)
    @ApiOperation(value = "批量查询商品信息", notes="根据指定条件批量查询商品信息，参数为GoodsDTO")
    BaseResponse getGoodsInfos(@RequestBody GoodsRequest goodsRequest){
        return feign.getGoodsInfos(goodsRequest);
    }
}
