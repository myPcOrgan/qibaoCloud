package com.qibao.activity.service;

import com.qibao.activity.entity.BoxEO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IBoxService extends IBaseService<BoxEO> {

    String insertBox(BoxEO boxEO);

    String deleteBox(Long id);

    String updateBox(BoxEO boxEO);

    List<BoxEO> selectBoxList(BoxEO boxEO);

    BoxEO selectByIdForUpdate(Long id);

    List<BoxVO> selectBoxDetailList(String boxName);
}
