package com.qibao.backend.model;

import java.util.Date;

/**
 * Created by 周黎钢 on 2018/1/25.
 * 客服VO
 */
public class ServiceVO {
    private Long id;
    /**
     * 客服QQ
     */
    private String serviceQq;

    /**
     * 客服QQ昵称
     */
    private String serviceName;

    private Date lastUpdateTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceQq() {
        return serviceQq;
    }

    public void setServiceQq(String serviceQq) {
        this.serviceQq = serviceQq;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
