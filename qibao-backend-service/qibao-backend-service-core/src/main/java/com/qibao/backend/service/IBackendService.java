package com.qibao.backend.service;

import com.qibao.backend.entity.*;
import com.qibao.backend.model.BackendRequest;
import com.qibao.common.service.IBaseService;

import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/22.
 */
public interface IBackendService extends IBaseService<UserEO> {

    void insertUser(BackendRequest request);

    UserEO getUserById(Long id);

    void updateUser(BackendRequest request);

    List<UserEO> findBackUsers(BackendRequest request);

    Boolean loginCheck(BackendRequest request);

    void insertRole(BackendRequest request);

    void updateRole(BackendRequest request);

    List<RoleEO> findBackRoles(BackendRequest request);

    void insertFunction(BackendRequest request);

    void updateFunction(BackendRequest request);

    List<FunctionEO> findBackFunctions(BackendRequest request);

    void insertUserRole(BackendRequest request);

    void batchInsertUserRoles(BackendRequest request);

    void updateUserRole(BackendRequest request);

    List<UserWithRoleNameEO> findBackUserRoles(BackendRequest request);

    void insertRoleFunction(BackendRequest request);

    void batchInsertRoleFunctions(BackendRequest request);

    void updateRoleFunction(BackendRequest request);

    List<RoleWithFuncNameEO> findBackRoleFunctions(BackendRequest request);

}
