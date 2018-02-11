package com.qibao.goods.controller;

import com.qibao.common.dto.BaseResponse;
import com.qibao.goods.model.GoodsRequest;
import com.qibao.goods.model.GoodsVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goods")
public interface IGoodsController {
    @RequestMapping(value = "insertGoods", method = RequestMethod.POST)
    BaseResponse insertGoods(@RequestBody GoodsRequest goodsRequest);

    @RequestMapping(value = "updateGoods", method = RequestMethod.POST)
    BaseResponse updateGoods(@RequestBody GoodsRequest goodsRequest);

    @RequestMapping(value = "batchUpdateGoods", method = RequestMethod.POST)
    BaseResponse batchUpdateGoods(@RequestBody List<GoodsRequest> goodsRequests);

    @RequestMapping(value = "getGoodsInfoById", method = RequestMethod.GET)
    BaseResponse<GoodsVO> getGoodsInfoById(@RequestParam("id") Long id);

    @RequestMapping(value = "getGoodsInfos", method = RequestMethod.POST)
    BaseResponse<GoodsVO>  getGoodsInfos(@RequestBody GoodsRequest goodsRequest);

}
