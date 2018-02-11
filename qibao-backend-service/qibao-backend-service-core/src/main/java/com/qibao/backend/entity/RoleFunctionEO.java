package com.qibao.backend.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "t_backend_role_function")
public class RoleFunctionEO extends BaseEntity{
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "function_id")
    private Long functionId;

    /**
     * @return role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return function_id
     */
    public Long getFunctionId() {
        return functionId;
    }

    /**
     * @param functionId
     */
    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
}