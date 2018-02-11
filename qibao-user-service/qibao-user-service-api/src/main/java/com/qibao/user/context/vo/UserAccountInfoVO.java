package com.qibao.user.context.vo;



public class UserAccountInfoVO {

    /**
     * 用户id
     */
    private Long userId;

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
    private String weiXin;

    /**
     * 1.官方 2.主播 3.普通
     */
    private Byte userGrade;

    /**
     * 创建房间数
     */
    private Integer createRoomNum;

    /**
     * 加入房间数
     */
    private Integer joinRoomNum;

    /**
     * 获奖次数
     */
    private Integer winRoomNum;

    /**
     * 获奖金币
     */
    private Double winTotalGold;

    /**
     * 总金额
     */
    private Double totalGold;

    /**
     * 用户消息数
     */
    private Integer emailNum;

    public Integer getEmailNum() {
        return emailNum;
    }

    public void setEmailNum(Integer emailNum) {
        this.emailNum = emailNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    public Byte getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Byte userGrade) {
        this.userGrade = userGrade;
    }

    public Integer getCreateRoomNum() {
        return createRoomNum;
    }

    public void setCreateRoomNum(Integer createRoomNum) {
        this.createRoomNum = createRoomNum;
    }

    public Integer getJoinRoomNum() {
        return joinRoomNum;
    }

    public void setJoinRoomNum(Integer joinRoomNum) {
        this.joinRoomNum = joinRoomNum;
    }

    public Integer getWinRoomNum() {
        return winRoomNum;
    }

    public void setWinRoomNum(Integer winRoomNum) {
        this.winRoomNum = winRoomNum;
    }

    public Double getWinTotalGold() {
        return winTotalGold;
    }

    public void setWinTotalGold(Double winTotalGold) {
        this.winTotalGold = winTotalGold;
    }

    public Double getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(Double totalGold) {
        this.totalGold = totalGold;
    }
}