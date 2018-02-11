package com.qibao.backend.mapper;

import com.qibao.backend.entity.UserRoleEO;
import com.qibao.backend.entity.UserWithRoleNameEO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface UserRoleEOMapper extends IBaseMapper<UserRoleEO> {
    List<UserWithRoleNameEO> findUserRoles(Map map);

    void batchInsertUserRoles(List<UserRoleEO> userRoleEOs);
}