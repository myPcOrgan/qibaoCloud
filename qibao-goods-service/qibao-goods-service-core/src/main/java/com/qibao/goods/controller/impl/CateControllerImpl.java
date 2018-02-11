package com.qibao.goods.controller.impl;

import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.BeanMapper;
import com.qibao.goods.controller.ICategoryController;
import com.qibao.goods.entity.CategoryEO;
import com.qibao.goods.model.CategoryRequest;
import com.qibao.goods.model.CategoryVO;
import com.qibao.goods.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/11.
 */
@RestController
@RequestMapping(value = "category")
public class CateControllerImpl implements ICategoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ICategoryService categoryService;

    @Override
    @RequestMapping(value = "insertCate", method = RequestMethod.GET)
    public BaseResponse insert(@RequestBody CategoryRequest categoryRequest) {
        BaseResponse<CategoryRequest> response = new BaseResponse<>();
        categoryService.insertCate(categoryRequest);
        response.setResult(categoryRequest);
        logger.info("添加类目成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), categoryRequest);
        response.setMessage("插入类目成功");
        response.setSuccess();
        return response;
    }

    @Override
    @RequestMapping(value = "updateCateInfo", method = RequestMethod.POST)
    public BaseResponse update(@RequestBody CategoryRequest categoryRequest) {
        BaseResponse<CategoryRequest> response = new BaseResponse<>();
        categoryService.updateCate(categoryRequest);
        logger.info("更新类目成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), categoryRequest);
        response.setSuccess();
        response.setMessage("更新类目成功");
        return response;
    }

    @Override
    @RequestMapping(value = "getCateInfoById", method = RequestMethod.GET)
    public BaseResponse<CategoryVO> getCateInfoById(@RequestParam("id") Long id) {
        BaseResponse<CategoryVO> response = new BaseResponse<>();
        Assert.notNull(id, "id不能为空");
        CategoryEO categoryEO = categoryService.getCateInfo(id);
        CategoryVO categoryVO = new CategoryVO();
        BeanMapper.copyPropertiesIgnoreNull(categoryEO, categoryVO);
        response.setResult(categoryVO);
        logger.info("查找类目成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), id);
        response.setSuccess();
        response.setMessage("查找类目成功");
        return response;
    }

    @Override
    @RequestMapping(value = "getCateInfos", method = RequestMethod.POST)
    public BaseResponse<CategoryVO> getCateInfos(@RequestBody CategoryRequest categoryRequest) {
        BaseResponse<CategoryVO> response = new BaseResponse<>();
        List<CategoryVO> categoryRequests = BeanMapper.mapList(categoryService.getCateInfos(categoryRequest), CategoryVO.class);
        response.setData(categoryRequests);
        logger.info("批量查找类目成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), categoryRequest);
        response.setTotalCount(categoryRequests.size());
        response.setSuccess();
        response.setMessage("批量查找类目成功");
        return response;
    }
}
