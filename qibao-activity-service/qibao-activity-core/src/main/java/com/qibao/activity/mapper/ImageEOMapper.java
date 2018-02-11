package com.qibao.activity.mapper;

import com.qibao.activity.entity.ImageEO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface ImageEOMapper extends IBaseMapper<ImageEO> {

    /**
     * 分页查询图片
     */
    List<ImageEO> selectImageListPage(Map<String, Object> queryMap);
}