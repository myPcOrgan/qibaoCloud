package com.qibao.backend.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "t_backend_user_role")
public class UserRoleEO extends BaseEntity{
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

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
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}