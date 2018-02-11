package com.qibao.backend.service;

import com.qibao.backend.entity.BackendLogEO;
import com.qibao.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Created by 340067 on 2018/1/25.
 */
public interface IBackendLogService extends IBaseService<BackendLogEO> {

    List<BackendLogEO> listMessageByMap(Map<String, Object> params);

    int countByMap(Map<String, Object> params);
}
