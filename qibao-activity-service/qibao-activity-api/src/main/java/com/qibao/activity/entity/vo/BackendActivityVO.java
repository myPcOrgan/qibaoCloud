package com.qibao.activity.entity.vo;

import java.util.Date;

/**
 * 后台活动列表
 */
public class BackendActivityVO {
    //活动id
    private Long activityId;
    //活动名称
    private String activityName;
    //活动奖品总数
    private Double totalGold;
    //活动参与人数
    private Integer peopleNum;
    //活动开始时间
    private Date startTime;
    //活动结束时间
    private Date endTime;
    //活动状态
    private Byte activityStatus;
    //活动内容
    private String description;
    //活动审核状态
    private Byte verifyStatus;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Byte activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Byte verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
