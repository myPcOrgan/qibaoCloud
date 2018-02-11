package com.qibao.backend.model;


import java.util.Date;

public class BackendLogVO{

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作IP
     *
     * @return ip - 操作IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置操作IP
     *
     * @param ip 操作IP
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取描述
     *
     * @return descripe - 描述
     */
    public String getDescripe() {
        return descripe;
    }

    /**
     * 设置描述
     *
     * @param descripe 描述
     */
    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

    /**
     * 获取模块类型(0登录，1用户，2活动，3商品，4订单)
     *
     * @return module_type - 模块类型(0登录，1用户，2活动，3商品，4订单)
     */
    public Integer getModuleType() {
        return moduleType;
    }

    /**
     * 设置模块类型(0登录，1用户，2活动，3商品，4订单)
     *
     * @param moduleType 模块类型(0登录，1用户，2活动，3商品，4订单)
     */
    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}