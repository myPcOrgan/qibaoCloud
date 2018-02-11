package com.qibao.user.entity;


import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_user")
public class UserAccountEO extends BaseEntity {

    /**
     * 用户账号(唯一不可修改)
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 头像
     */
    @Column(name = "user_img")
    private String userImg;

    /**
     * 手机号
     */
    @Column(name = "mobile_phone")
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
     * QQ唯一码
     */
    @Column(name = "qq_code")
    private String qqCode;

    /**
     * 微信唯一码
     */
    @Column(name = "wechart_code")
    private String wechartCode;

    /**
     * 创建用户时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最后一次退出时间
     */
    @Column(name = "last_loginout_time")
    private Date lastLoginoutTime;

    /**
     * 最后一次修改时间（修改用户信息）
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 是否禁用
     */
    @Column(name = "is_forbid")
    private Boolean isForbid;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     *  1.普通 2.官方 3.主播
     */
    @Column(name = "user_grade")
    private Integer userGrade;

    /**
     * 用户注册IP
     */
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 用户注册设备
     */
    @Column(name = "register_device")
    private String registerDevice;

    /**
     * 用户登录类型， 3.手机登录 2.微信登录 1.QQ登录
     */
    @Column(name = "login_type")
    private Integer loginType;

    /**
     * 总金额
     */
    @Column(name = "total_amount")
    private Double totalAmount;

    /**
     * 连续超本金中奖次数
     */
    @Column(name = "exceed_count")
    private Integer exceedCount;

    /**
     * 是否禁止创建房间
     */
    @Column(name = "is_forbid_create_room")
    private Boolean isForbidCreateRoom;

    public Boolean getForbidCreateRoom() {
        return isForbidCreateRoom;
    }

    public void setForbidCreateRoom(Boolean forbidCreateRoom) {
        isForbidCreateRoom = forbidCreateRoom;
    }

    public Integer getExceedCount() {
        return exceedCount;
    }

    public void setExceedCount(Integer exceedCount) {
        this.exceedCount = exceedCount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterDevice() {
        return registerDevice;
    }

    public void setRegisterDevice(String registerDevice) {
        this.registerDevice = registerDevice;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

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

    /**
     * 获取QQ唯一码
     *
     * @return qq_code - QQ唯一码
     */
    public String getQqCode() {
        return qqCode;
    }

    /**
     * 设置QQ唯一码
     *
     * @param qqCode QQ唯一码
     */
    public void setQqCode(String qqCode) {
        this.qqCode = qqCode;
    }

    /**
     * 获取微信唯一码
     *
     * @return wechart_code - 微信唯一码
     */
    public String getWechartCode() {
        return wechartCode;
    }

    /**
     * 设置微信唯一码
     *
     * @param wechartCode 微信唯一码
     */
    public void setWechartCode(String wechartCode) {
        this.wechartCode = wechartCode;
    }

    /**
     * 获取创建用户时间
     *
     * @return create_time - 创建用户时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建用户时间
     *
     * @param createTime 创建用户时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login_time - 最后一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLoginTime 最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取最后一次退出时间
     *
     * @return last_loginout_time - 最后一次退出时间
     */
    public Date getLastLoginoutTime() {
        return lastLoginoutTime;
    }

    /**
     * 设置最后一次退出时间
     *
     * @param lastLoginoutTime 最后一次退出时间
     */
    public void setLastLoginoutTime(Date lastLoginoutTime) {
        this.lastLoginoutTime = lastLoginoutTime;
    }

    /**
     * 获取最后一次修改时间（修改用户信息）
     *
     * @return last_update_time - 最后一次修改时间（修改用户信息）
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后一次修改时间（修改用户信息）
     *
     * @param lastUpdateTime 最后一次修改时间（修改用户信息）
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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
}