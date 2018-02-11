package com.qibao.backend.mapper;

import com.qibao.backend.entity.BackendLogEO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface BackendLogEOMapper extends IBaseMapper<BackendLogEO> {

    /**
     * 根据条件查询包括分页，排序
     * @param params
     * @return
     */
    List<BackendLogEO> listBackendLogByMap(Map<String, Object> params);


    int countByMap(Map<String, Object> params);
}