package com.qibao.goods.service;

import com.qibao.common.service.IBaseService;
import com.qibao.goods.entity.CategoryEO;
import com.qibao.goods.model.CategoryRequest;

import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/12.
 */
public interface ICategoryService extends IBaseService<CategoryEO> {
    void insertCate(CategoryRequest categoryRequest);

    void updateCate(CategoryRequest categoryRequest);

    CategoryEO getCateInfo(Long id);

    List<CategoryEO> getCateInfos(CategoryRequest categoryRequest);
}
