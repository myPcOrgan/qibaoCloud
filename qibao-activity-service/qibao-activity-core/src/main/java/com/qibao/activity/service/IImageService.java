package com.qibao.activity.service;

import com.qibao.activity.entity.ImageEO;
import com.qibao.activity.entity.dto.ImageDTO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IImageService extends IBaseService<ImageEO> {

    List<ImageEO> selectImageListPage(ImageDTO imageDTO);
}
