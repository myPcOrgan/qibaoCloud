package com.qibao.user.context.dto;


public class UserAccountDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户账号(唯一不可修改)
     */
    private String userAccount;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String userImg;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * QQ号码
     */
    private String qq;

    /**
     * 微信号码
     */
    private String wechart;

    /**
     * 创建用户时间
     */
    private String createStartTime;

    private String createEndTime;

    /**
     * 最后一次登录时间
     */
    private String lastLoginStartTime;

    private String lastLoginEndTime;

    /**
     * 最后一次退出时间
     */
    private String lastLoginoutStartTime;

    private String lastLoginoutEndTime;

    /**
     * 最后一次修改时间（修改用户信息）
     */
    private String lastUpStringStartTime;

    private String lastUpStringEndTime;

    /**
     * 是否禁用
     */
    private Boolean isForbid;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 1.官方 2.主播 3.普通
     */
    private Integer userGrade;

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

    /**
     * 获取用户账号(唯一不可修改)
     *
     * @return user_account - 用户账号(唯一不可修改)
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户账号(唯一不可修改)
     *
     * @param userAccount 用户账号(唯一不可修改)
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取头像
     *
     * @return user_img - 头像
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * 设置头像
     *
     * @param userImg 头像
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取手机号
     *
     * @return mobile_phone - 手机号
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号
     *
     * @param mobilePhone 手机号
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取QQ号码
     *
     * @return qq - QQ号码
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号码
     *
     * @param qq QQ号码
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取微信号码
     *
     * @return wechart - 微信号码
     */
    public String getWechart() {
        return wechart;
    }

    /**
     * 设置微信号码
     *
     * @param wechart 微信号码
     */
    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getLastLoginStartTime() {
        return lastLoginStartTime;
    }

    public void setLastLoginStartTime(String lastLoginStartTime) {
        this.lastLoginStartTime = lastLoginStartTime;
    }

    public String getLastLoginEndTime() {
        return lastLoginEndTime;
    }

    public void setLastLoginEndTime(String lastLoginEndTime) {
        this.lastLoginEndTime = lastLoginEndTime;
    }

    public String getLastLoginoutStartTime() {
        return lastLoginoutStartTime;
    }

    public void setLastLoginoutStartTime(String lastLoginoutStartTime) {
        this.lastLoginoutStartTime = lastLoginoutStartTime;
    }

    public String getLastLoginoutEndTime() {
        return lastLoginoutEndTime;
    }

    public void setLastLoginoutEndTime(String lastLoginoutEndTime) {
        this.lastLoginoutEndTime = lastLoginoutEndTime;
    }

    public String getLastUpStringStartTime() {
        return lastUpStringStartTime;
    }

    public void setLastUpStringStartTime(String lastUpStringStartTime) {
        this.lastUpStringStartTime = lastUpStringStartTime;
    }

    public String getLastUpStringEndTime() {
        return lastUpStringEndTime;
    }

    public void setLastUpStringEndTime(String lastUpStringEndTime) {
        this.lastUpStringEndTime = lastUpStringEndTime;
    }

    /**
     * 获取是否禁用
     *
     * @return is_forbid - 是否禁用
     */
    public Boolean getIsForbid() {
        return isForbid;
    }

    /**
     * 设置是否禁用
     *
     * @param isForbid 是否禁用
     */
    public void setIsForbid(Boolean isForbid) {
        this.isForbid = isForbid;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取1.官方 2.主播 3.普通
     *
     * @return user_grade - 1.官方 2.主播 3.普通
     */
    public Integer getUserGrade() {
        return userGrade;
    }

    /**
     * 设置1.官方 2.主播 3.普通
     *
     * @param userGrade 1.官方 2.主播 3.普通
     */
    public void setUserGrade(Integer userGrade) {
        this.userGrade = userGrade;
    }

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
}