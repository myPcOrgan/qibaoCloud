package com.qibao.goods;

import com.qibao.goods.entity.CategoryEO;
import com.qibao.goods.entity.GoodsEO;
import com.qibao.goods.model.CategoryRequest;
import com.qibao.goods.model.GoodsRequest;
import com.qibao.goods.redis.IGoodsRedis;
import com.qibao.goods.service.ICategoryService;
import com.qibao.goods.service.IGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/10.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoodsServer.class)
@WebAppConfiguration
public class GetGoodsInfoTest {
    @Autowired
    private IGoodsRedis goodsRedis;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ICategoryService categoryService;
    @Test
    public void testdfdf() {
        goodsRedis.saveGoodsInfo();
        System.out.println(goodsRedis.getGoodsInfo());
        System.out.println(new Date());
    }

    @Test
    public void testGetGoodsInfo() {
        System.out.println(goodsService.getGoodsInfo(5L));
    }

    @Test
    public void testGetGoodsInfos() {
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setGoodsUnit("件");
        List<GoodsEO> goodsEOs = goodsService.getGoodsInfos(goodsRequest);
        for (GoodsEO goodsEO1 : goodsEOs) {
            System.out.println(goodsEO1);
        }
    }

    @Test
    public void testInsertGoods() {
        GoodsRequest goodsRequest = new GoodsRequest();
        goodsRequest.setGoodsUnit("件");
        goodsRequest.setGoodsAmount(10000D);
        goodsRequest.setGoodsNum(1L);
        goodsRequest.setGoodsName("倚天剑");
        goodsService.insertGoods(goodsRequest);
    }

    @Test
    public void testBathUpdateGoods() {
        List list=new LinkedList<>();
        GoodsRequest goodsRequest=new GoodsRequest();
        goodsRequest.setId(5L);
        goodsRequest.setGoodsAmount(9000D);
//        goodsEO1.setGoodsNum(1000L);
        GoodsRequest goodsRequest1=new GoodsRequest();
        goodsRequest1.setId(6L);
        goodsRequest1.setGoodsAmount(10000D);
//        goodsEO2.setGoodsNum(1L);
        list.add(goodsRequest);
        list.add(goodsRequest1);
        goodsService.batchUpdate(list);
    }

    @Test
    public void testGetCategory() {
        System.out.println(categoryService.getCateInfo(5L));
        System.out.println(new GoodsEO()==null);
    }

    @Test
    public void testGetGoodsCategorys() {
        CategoryRequest request=new CategoryRequest();
//        request.setGoodsUnit("件");
        List<CategoryEO> categoryEOs = categoryService.getCateInfos(request);
        for (CategoryEO categoryEO1 : categoryEOs) {
            System.out.println(categoryEO1);
        }
    }

    @Test
    public void testInsertCategory() {
        CategoryRequest request=new CategoryRequest();
        request.setCateCode("2");
        request.setCateName("倚天剑");
        request.setGoodsUnit("件");
//        categoryEO.setCateLevel(3);
//        categoryEO.setParentId(12L);
        categoryService.insertCate(request);
    }

    @Test
    public void testDeleteCategory() {
        CategoryEO categoryEO=new CategoryEO();
        categoryEO.setId(1L);
        categoryService.delete(categoryEO);
    }
}
