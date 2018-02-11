package com.qibao.activity.entity.vo;

import java.util.Date;

/**
 * 房间列表接口专业
 */
public class RoomListVO {

    //房间id
    private Long roomId;
    //活动id
    private Long activityId;
    //房间名称
    private String roomName;
    //房间号
    private String roomNo;
    //用户头像
    private String userImg;
    //活动模式
    private Byte activityType;
    //开奖模式
    private Byte lotteryType;
    //开奖总金币
    private Double totalGold;
    //开奖时间
    private Date lotteryTime;
    //参与人数
    private Integer peopleNum;
    //总价值
    private Double totalRmb;
    //房间最大开奖人数
    private Integer maxJoinPeople;


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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Byte getActivityType() {
        return activityType;
    }

    public void setActivityType(Byte activityType) {
        this.activityType = activityType;
    }

    public Byte getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(Byte lotteryType) {
        this.lotteryType = lotteryType;
    }

    public Double getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(Double totalGold) {
        this.totalGold = totalGold;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Double getTotalRmb() {
        return totalRmb;
    }

    public void setTotalRmb(Double totalRmb) {
        this.totalRmb = totalRmb;
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
}
