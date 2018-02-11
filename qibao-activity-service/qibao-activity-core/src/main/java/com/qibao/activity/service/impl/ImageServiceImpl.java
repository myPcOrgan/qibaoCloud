package com.qibao.activity.service.impl;

import com.qibao.activity.entity.ImageEO;
import com.qibao.activity.entity.dto.ImageDTO;
import com.qibao.activity.mapper.ImageEOMapper;
import com.qibao.activity.service.IImageService;
import com.qibao.common.service.abs.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageServiceImpl extends BaseService<ImageEO> implements IImageService {

    @Autowired
    ImageEOMapper imageEOMapper;

    @Override
    public List<ImageEO> selectImageListPage(ImageDTO imageDTO) {
        Map<String, Object> queryMap = new HashMap<>();
        if (StringUtils.isNotBlank(imageDTO.getImgName())) {
            queryMap.put("imgName", imageDTO.getImgName());
        }
        if (imageDTO.getLimit() != null) {
            queryMap.put("limit", imageDTO.getLimit());
            if (imageDTO.getStart() != null && imageDTO.getStart() > 0) {
                queryMap.put("start", imageDTO.getStart());
            } else {
                queryMap.put("start", 0);
            }
        }
        List<ImageEO> imageEOList = imageEOMapper.selectImageListPage(queryMap);
        return imageEOList;
    }

}
