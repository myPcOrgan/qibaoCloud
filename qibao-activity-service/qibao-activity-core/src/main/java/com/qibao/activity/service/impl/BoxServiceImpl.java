package com.qibao.activity.service.impl;

import com.qibao.activity.entity.BoxEO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.activity.mapper.BoxEOMapper;
import com.qibao.activity.service.IBoxService;
import com.qibao.common.service.abs.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoxServiceImpl extends BaseService<BoxEO> implements IBoxService {

    @Autowired
    private BoxEOMapper boxEOMapper;

    @Override
    public String insertBox(BoxEO boxEO) {
        boxEOMapper.insert(boxEO);
        return "";
    }

    @Override
    public String deleteBox(Long id) {
        return "";
    }

    @Override
    public String updateBox(BoxEO boxEO) {

        return "";
    }

    @Override
    public List<BoxEO> selectBoxList(BoxEO boxEO) {
        List<BoxEO> boxEOS = boxEOMapper.select(boxEO);
        return boxEOS;
    }

    @Override
    public BoxEO selectByIdForUpdate(Long id) {
        if (id != null) {
            return boxEOMapper.selectByIdForUpdate(id);
        } else {
            return null;
        }
    }

    @Override
    public List<BoxVO> selectBoxDetailList(String boxName) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("boxName", boxName);
        List<BoxVO> boxVOList = boxEOMapper.selectBoxDetailList(queryMap);
        return boxVOList;
    }
}
