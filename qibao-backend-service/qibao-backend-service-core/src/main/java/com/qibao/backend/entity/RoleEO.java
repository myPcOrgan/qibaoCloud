package com.qibao.backend.entity;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.qibao.common.entity.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_backend_role")
public class RoleEO extends BaseEntity{
    /**
     * 角色名字
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 是否删除，1为已删
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 获取角色名字
     *
     * @return role_name - 角色名字
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名字
     *
     * @param roleName 角色名字
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return role_desc - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return last_update_time
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取是否删除，1为已删
     *
     * @return is_deleted - 是否删除，1为已删
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除，1为已删
     *
     * @param isDeleted 是否删除，1为已删
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}