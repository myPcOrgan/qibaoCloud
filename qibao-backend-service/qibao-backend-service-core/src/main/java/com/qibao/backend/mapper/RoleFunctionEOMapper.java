package com.qibao.backend.mapper;

import com.qibao.backend.entity.RoleFunctionEO;
import com.qibao.backend.entity.RoleWithFuncNameEO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface RoleFunctionEOMapper extends IBaseMapper<RoleFunctionEO> {
    List<RoleWithFuncNameEO> findRoleFunctions(Map map);

    void batchInsertRoleFunctions(List<RoleFunctionEO>roleFunctionEOs);
}