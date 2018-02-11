package com.qibao.backend.redis;

import com.qibao.backend.entity.FunctionEO;

import java.util.List;

/**
 * Created by 周黎钢 on 2018/2/5.
 */
public interface IBackendRedisDao {
    void setBackendFunctions(List<FunctionEO> functionEOs);

    List<FunctionEO> getBackendFunctions();
}
