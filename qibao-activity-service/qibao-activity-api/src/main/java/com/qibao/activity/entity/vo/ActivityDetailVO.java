package com.qibao.activity.entity.vo;

import java.util.Date;

/**
 * 活动详情接口专用
 */
public class ActivityDetailVO {

    //房间id
    private Long roomId;
    //活动id
    private Long activityId;
    //用户id
    private Long userId;
    //用户头像
    private String userImg;
    //房间名称
    private String roomName;
    //房间号
    private String roomNo;
    //是否有直播间
    private Byte isLiveRoom;
    //直播间地址
    private String liveRoomUrl;
    //房间描述
    private String description;
    //开奖模式
    private Byte lotteryType;
    //开奖时间
    private Date lotteryTime;
    //活动模式
    private Byte activityType;
    //开奖总金币
    private Double totalGold;
    //参与人数
    private Integer peopleNum;
    //获奖人数
    private Integer lotteryNum;
    //奖品总价值
    private Double totalRmb;
    //点赞数
    private Integer totalUpvote;
    //房间最大参与人数
    private Integer maxJoinPeople;
    //活动状态0已结束1已开始2已创建4被禁止
    private Byte activeStatus;
    //是否参与该活动
    private boolean joinActivity;


    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Byte getIsLiveRoom() {
        return isLiveRoom;
    }

    public void setIsLiveRoom(Byte isLiveRoom) {
        this.isLiveRoom = isLiveRoom;
    }

    public String getLiveRoomUrl() {
        return liveRoomUrl;
    }

    public void setLiveRoomUrl(String liveRoomUrl) {
        this.liveRoomUrl = liveRoomUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(Byte lotteryType) {
        this.lotteryType = lotteryType;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    public Byte getActivityType() {
        return activityType;
    }

    public void setActivityType(Byte activityType) {
        this.activityType = activityType;
    }

    public Double getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(Double totalGold) {
        this.totalGold = totalGold;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public Double getTotalRmb() {
        return totalRmb;
    }

    public void setTotalRmb(Double totalRmb) {
        this.totalRmb = totalRmb;
    }

    public Integer getTotalUpvote() {
        return totalUpvote;
    }

    public void setTotalUpvote(Integer totalUpvote) {
        this.totalUpvote = totalUpvote;
    }

    public Integer getMaxJoinPeople() {
        return maxJoinPeople;
    }

    public void setMaxJoinPeople(Integer maxJoinPeople) {
        this.maxJoinPeople = maxJoinPeople;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Byte getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Byte activeStatus) {
        this.activeStatus = activeStatus;
    }

    public boolean isJoinActivity() {
        return joinActivity;
    }

    public void setJoinActivity(boolean joinActivity) {
        this.joinActivity = joinActivity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
