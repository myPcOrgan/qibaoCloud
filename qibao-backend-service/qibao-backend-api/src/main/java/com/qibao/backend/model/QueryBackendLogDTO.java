package com.qibao.backend.model;

import java.util.Date;
import java.util.List;

/**
 * Created by 340067 on 2018/1/25.
 */
public class QueryBackendLogDTO {
    private Long id;


    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 描述
     */
    private String descripe;

    /**
     * 模块类型(0登录，1用户，2活动，3商品，4订单)
     */
    private Integer moduleType;

    private List<Integer> moduleTypeList;

    private Date createStartTime;

    private Date createEndTime;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 是否升序排列  true 为升序
     */
    private Boolean isAsc;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getAsc() {
        return isAsc;
    }

    public void setAsc(Boolean asc) {
        isAsc = asc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public List<Integer> getModuleTypeList() {
        return moduleTypeList;
    }

    public void setModuleTypeList(List<Integer> moduleTypeList) {
        this.moduleTypeList = moduleTypeList;
    }

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }
}
