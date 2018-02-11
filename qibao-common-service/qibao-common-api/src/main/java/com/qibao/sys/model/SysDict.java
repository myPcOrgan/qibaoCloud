package com.qibao.goods.model;

import com.qibao.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class SysDict  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long fdParent;
    private String dictCode;
    private String name;
    private String remark;
    private Date createTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFdParent() {
        return fdParent;
    }

    public void setFdParent(Long fdParent) {
        this.fdParent = fdParent;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




}