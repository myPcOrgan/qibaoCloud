package com.qibao.goods.controller;

import com.qibao.common.dto.BaseResponse;
import com.qibao.goods.model.CategoryRequest;
import com.qibao.goods.model.CategoryVO;

/**
 * Created by 周黎钢 on 2018/1/11.
 */
public interface ICategoryController{
    BaseResponse insert(CategoryRequest categoryRequest);

    BaseResponse update(CategoryRequest categoryRequest);

    BaseResponse<CategoryVO> getCateInfoById(Long id);

    BaseResponse<CategoryVO> getCateInfos(CategoryRequest categoryRequest);

}
