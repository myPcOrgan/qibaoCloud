package com.qibao.goods.entity;

import com.qibao.common.entity.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_dict")
public class SysDictEO extends BaseEntity {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父分类
     */
    @Column(name = "fd_parent")
    private Long fdParent;

    /**
     * 键
            
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 值
            
     */
    private String name;

    /**
     * 说明
            
     */
    private String remark;

    /**
     * 创建时间
            
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
            
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 0是否1是已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取父分类
     *
     * @return fd_parent - 父分类
     */
    public Long getFdParent() {
        return fdParent;
    }

    /**
     * 设置父分类
     *
     * @param fdParent 父分类
     */
    public void setFdParent(Long fdParent) {
        this.fdParent = fdParent;
    }

    /**
     * 获取键
            
     *
     * @return dict_code - 键
            
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置键
            
     *
     * @param dictCode 键
            
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取值
            
     *
     * @return name - 值
            
     */
    public String getName() {
        return name;
    }

    /**
     * 设置值
            
     *
     * @param name 值
            
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取说明
            
     *
     * @return remark - 说明
            
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置说明
            
     *
     * @param remark 说明
            
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
            
     *
     * @return create_time - 创建时间
            
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
            
     *
     * @param createTime 创建时间
            
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
            
     *
     * @return last_update_time - 修改时间
            
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置修改时间
            
     *
     * @param lastUpdateTime 修改时间
            
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取0是否1是已删除
     *
     * @return is_deleted - 0是否1是已删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置0是否1是已删除
     *
     * @param isDeleted 0是否1是已删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取是否启用
     *
     * @return is_enable - 是否启用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用
     *
     * @param isEnable 是否启用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}