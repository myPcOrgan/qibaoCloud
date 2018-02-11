package com.qibao.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.goods.entity.CategoryEO;
import com.qibao.goods.exception.GoodsException;
import com.qibao.goods.model.CategoryRequest;
import com.qibao.goods.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/12.
 */
@Service
public class CategoryServiceImpl extends BaseService<CategoryEO> implements ICategoryService {
    @Override
    public void insertCate(CategoryRequest categoryRequest) {
        if (StringUtils.isBlank(categoryRequest.getCateCode())) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.CATE_CODE_NOT_NULL);
        }
        if (StringUtils.isBlank(categoryRequest.getCateName())) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.CATE_NAME_NOT_NULL);
        }
        if (categoryRequest.getCateLevel() == null) {
            categoryRequest.setCateLevel(0);
        } else if (categoryRequest.getParentId() != null) {
            CategoryEO fatherEO = this.selectById(categoryRequest.getParentId());
            StringBuffer levelId = new StringBuffer();
            if (StringUtils.isNotBlank(fatherEO.getLevelId())) {
                levelId.append(fatherEO.getLevelId() + ",");
            }
            levelId.append(categoryRequest.getParentId());
            categoryRequest.setLevelId(levelId.toString());
        }
        CategoryEO categoryEO = new CategoryEO();
        BeanMapper.copyPropertiesIgnoreNull(categoryRequest, categoryEO);
        categoryEO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        categoryEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        categoryEO.setIsDeleted(false);
        categoryEO.setIsEnable(true);
        this.insert(categoryEO);
    }

    @Override
    public void updateCate(CategoryRequest categoryRequest) {
        if (StringUtils.isBlank(categoryRequest.getCateCode())) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.CATE_CODE_NOT_NULL);
        }
        if (StringUtils.isBlank(categoryRequest.getCateName())) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.CATE_NAME_NOT_NULL);
        }
        if (categoryRequest.getIsDeleted() != null && !categoryRequest.getIsDeleted()) {
            throw new GoodsException(GoodsException.COMMON_EXCEPTION, GoodsException.IS_DELETED);
        }
        CategoryEO categoryEO = new CategoryEO();
        BeanMapper.copyPropertiesIgnoreNull(categoryRequest, categoryEO);
        categoryEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        this.update(categoryEO);
    }

    @Override
    public CategoryEO getCateInfo(Long id) {
        CategoryEO categoryEO = this.selectById(id);
        if (categoryEO.getIsDeleted()) {
            return null;
        }
        return categoryEO;
    }

    @Override
    public List<CategoryEO> getCateInfos(CategoryRequest categoryRequest) {
        Example example = new Example(CategoryEO.class);
        if (StringUtils.isNotBlank(categoryRequest.getOrderBy())) {
            if (categoryRequest.getIsAsc() != null) {
                example.setOrderByClause(categoryRequest.getOrderBy() + " " + (categoryRequest.getIsAsc() ? "asc" : "desc"));
            } else {
                example.setOrderByClause(categoryRequest.getOrderBy() + " desc");
            }
        } else {
            example.setOrderByClause("create_time desc");
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(categoryRequest);
        criteria.andEqualTo("isDeleted", false);
        if (categoryRequest.getPage() != null && categoryRequest.getPageSize() != null) {
            PageHelper.startPage(categoryRequest.getPage(), categoryRequest.getPageSize());
        }
        return this.selectByExample(example);
    }
}
