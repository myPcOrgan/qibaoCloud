package com.qibao.backend.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_backend_users")
public class UserEO extends BaseEntity{
    /**
     * 登录账户
     */
    @Column(name = "login_account")
    private String loginAccount;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否已删，1为已删
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 是否启用，1为启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 客服QQ
     */
    @Column(name = "service_qq")
    private String serviceQq;

    /**
     * 客服QQ昵称
     */
    @Column(name = "service_name")
    private String serviceName;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 获取登录账户
     *
     * @return login_account - 登录账户
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 设置登录账户
     *
     * @param loginAccount 登录账户
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取客服QQ
     *
     * @return service_qq - 客服QQ
     */
    public String getServiceQq() {
        return serviceQq;
    }

    /**
     * 设置客服QQ
     *
     * @param serviceQq 客服QQ
     */
    public void setServiceQq(String serviceQq) {
        this.serviceQq = serviceQq;
    }

    /**
     * 获取客服QQ昵称
     *
     * @return service_name - 客服QQ昵称
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 设置客服QQ昵称
     *
     * @param serviceName 客服QQ昵称
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取最后登录IP
     *
     * @return last_login_ip - 最后登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后登录IP
     *
     * @param lastLoginIp 最后登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}