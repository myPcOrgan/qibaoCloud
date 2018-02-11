package com.qibao.goods.controller.impl;

import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.BeanMapper;
import com.qibao.goods.controller.IGoodsController;
import com.qibao.goods.entity.GoodsEO;
import com.qibao.goods.model.GoodsRequest;
import com.qibao.goods.model.GoodsVO;
import com.qibao.goods.service.IGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("goods")
public class GoodsControllerImpl implements IGoodsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IGoodsService goodsService;

    @Override
    @RequestMapping(value = "insertGoods", method = RequestMethod.POST)
    public BaseResponse insertGoods(@RequestBody GoodsRequest goodsRequest) {
        BaseResponse<GoodsVO> response = new BaseResponse<>();
        goodsService.insertGoods(goodsRequest);
        logger.info("添加商品成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), goodsRequest);
        response.setSuccess();
        response.setMessage("添加商品成功");
        return response;
    }

    @Override
    @RequestMapping(value = "updateGoods", method = RequestMethod.POST)
    public BaseResponse updateGoods(@RequestBody GoodsRequest goodsRequest) {
        BaseResponse<GoodsRequest> response = new BaseResponse<>();
        goodsService.updateGoods(goodsRequest);
        logger.info("更新商品成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), goodsRequest);
        response.setSuccess();
        response.setMessage("更新商品成功");
        return response;
    }

    @Override
    @RequestMapping(value = "batchUpdateGoods", method = RequestMethod.POST)
    public BaseResponse batchUpdateGoods(@RequestBody List<GoodsRequest> goodsRequests) {
        BaseResponse<GoodsRequest> response = new BaseResponse<>();
        goodsService.batchUpdate(goodsRequests);
        logger.info("批量更新商品成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), goodsRequests);
        response.setSuccess();
        response.setMessage("批量更新商品成功");
        return response;
    }

    @Override
    @RequestMapping(value = "getGoodsInfoById", method = RequestMethod.GET)
    public BaseResponse<GoodsVO> getGoodsInfoById(@RequestParam("id") Long id) {
        BaseResponse<GoodsVO> response = new BaseResponse<>();
        Assert.notNull(id, "id不能为空");
        GoodsEO goodsEO = goodsService.getGoodsInfo(id);
        GoodsVO goodsVO = new GoodsVO();
        BeanMapper.copyPropertiesIgnoreNull(goodsEO, goodsVO);
        response.setResult(goodsVO);
        logger.info("查询商品成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), id);
        response.setSuccess();
        response.setMessage("查询商品成功");
        return response;
    }

    @Override
    @RequestMapping(value = "getGoodsInfos", method = RequestMethod.POST)
    public BaseResponse<GoodsVO> getGoodsInfos(@RequestBody GoodsRequest goodsRequest) {
        BaseResponse<GoodsVO> response = new BaseResponse<>();
        List<GoodsEO> goodsEOList = goodsService.getGoodsInfos(goodsRequest);
        List<GoodsVO> goodsVOs = BeanMapper.mapList(goodsEOList, GoodsVO.class);
        response.setData(goodsVOs);
        response.setTotalCount(goodsVOs.size());
        logger.info("批量查询商品成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), goodsRequest);
        response.setSuccess();
        response.setMessage("批量查询商品成功");
        return response;
    }
}
