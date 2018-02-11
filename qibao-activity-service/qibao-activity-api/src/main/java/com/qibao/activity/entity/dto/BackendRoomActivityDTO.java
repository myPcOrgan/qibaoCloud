package com.qibao.activity.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 后台房间活动dto
 */
public class BackendRoomActivityDTO {

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房间id
     */
    private Long roomId;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 房间名称（默认用户昵称）
     */
    private String roomName;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 开奖模式(0到时间开奖，1人数满了开奖，默认为0)
     */
    private Byte lotteryType;

    /**
     * 开奖时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lotteryTime;

    /**
     * 是否有直播间(0没有1有 默认0)
     */
    private Byte isLiveRoom;

    /**
     * 直播间url
     */
    private String liveRoomUrl;

    /**
     * 活动模式(0公开1私密，默认0)
     */
    private Byte activityType;

    /**
     * 房间密码
     */
    private String roomPassword;

    /**
     * 奖品总数（金币）
     */
    private Double totalGold;

    /**
     * 开奖人数(开奖模式的一种)
     */
    private Integer lotteryNum;

    /**
     * 房间描述
     */
    private String description;

    /**
     * 第几页
     */
    private Integer pageIndex;
    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 当开奖模式为人满开奖时，这个值要大于0
     */
    private Integer maxJoinPeople;

    /**
     * 活动名称
     */
    private String activityName;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public Byte getActivityType() {
        return activityType;
    }

    public void setActivityType(Byte activityType) {
        this.activityType = activityType;
    }

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public Double getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(Double totalGold) {
        this.totalGold = totalGold;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getMaxJoinPeople() {
        return maxJoinPeople;
    }

    public void setMaxJoinPeople(Integer maxJoinPeople) {
        this.maxJoinPeople = maxJoinPeople;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
