package com.qibao.user.dao;


import com.qibao.common.mapper.IBaseMapper;
import com.qibao.user.entity.MessageEO;

import java.util.List;
import java.util.Map;

public interface MessageEOMapper extends IBaseMapper<MessageEO> {

    /**
     * 根据条件查询包括分页，排序
     * @param params
     * @return
     */
    List<MessageEO> listMessageByMap(Map<String, Object> params);


    int countByMap(Map<String, Object> params);

    double getSumByMap(Map<String, Object> params);
}