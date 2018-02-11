package com.qibao.backend.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_backend_function")
public class FunctionEO extends BaseEntity{
    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限名字
     */
    @Column(name = "function_name")
    private String functionName;

    /**
     * 权限路径
     */
    private String uri;

    /**
     * 权限描述
     */
    @Column(name = "function_desc")
    private String functionDesc;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 是否启用，1为启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 是否已删，1为已删
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 获取父级ID
     *
     * @return parent_id - 父级ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级ID
     *
     * @param parentId 父级ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取权限名字
     *
     * @return function_name - 权限名字
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * 设置权限名字
     *
     * @param functionName 权限名字
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * 获取权限路径
     *
     * @return uri - 权限路径
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置权限路径
     *
     * @param uri 权限路径
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 获取权限描述
     *
     * @return function_desc - 权限描述
     */
    public String getFunctionDesc() {
        return functionDesc;
    }

    /**
     * 设置权限描述
     *
     * @param functionDesc 权限描述
     */
    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
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
     * 获取是否启用，1为启用
     *
     * @return is_enable - 是否启用，1为启用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用，1为启用
     *
     * @param isEnable 是否启用，1为启用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取是否已删，1为已删
     *
     * @return is_deleted - 是否已删，1为已删
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否已删，1为已删
     *
     * @param isDeleted 是否已删，1为已删
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "FunctionEO{" +
                "id=" + getId() +
                ",parentId=" + parentId +
                ", functionName='" + functionName + '\'' +
                ", uri='" + uri + '\'' +
                ", functionDesc='" + functionDesc + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isEnable=" + isEnable +
                ", isDeleted=" + isDeleted +
                '}';
    }
}