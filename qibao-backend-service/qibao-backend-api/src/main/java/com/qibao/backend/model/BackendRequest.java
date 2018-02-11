package com.qibao.backend.model;

import com.qibao.common.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/25.
 * 请求参数类
 */
public class BackendRequest extends BaseEntity {
    private Long id;
    /**
     * 登录账户
     */
    private String loginAccount;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 是否已删，1为已删
     */
    private Boolean isDeleted;

    /**
     * 是否启用，1为启用
     */
    private Boolean isEnable;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 客服QQ
     */
    private String serviceQq;

    /**
     * 客服QQ昵称
     */
    private String serviceName;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 分页查询-第几页
     */
    private Integer page;

    /**
     * 分页查询-每页大小
     */
    private Integer pageSize;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * true为正序
     */
    private Boolean isAsc;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 权限ID
     */
    private Long functionId;

    /**
     * 是否是客服
     */
    private Boolean isService;

    /**
     * 角色名字
     */
    private String  roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 权限名字
     */
    private String functionName;

    /**
     * 权限描述
     */
    private String functionDesc;

    /**
     * 权限路径
     */
    private String uri;

    /**
     * 父类权限ID
     */
    private Long parentId;

    /**
     * 角色ID的集合
     */
    private List<Long> roleIds;

    /**
     * 权限ID的集合
     */
    private List<Long> functionIds;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean asc) {
        isAsc = asc;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Boolean getIsService() {
        return isService;
    }

    public void setIsService(Boolean service) {
        isService = service;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Long> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<Long> functionIds) {
        this.functionIds = functionIds;
    }
}