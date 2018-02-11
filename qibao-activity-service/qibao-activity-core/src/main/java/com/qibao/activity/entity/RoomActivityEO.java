package com.qibao.activity.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_room_activity")
public class RoomActivityEO extends BaseEntity {

    /**
     * 房间号
     */
    @Column(name = "room_id")
    private Long roomId;

    /**
     * 活动编号
     */
    @Column(name = "activity_no")
    private String activityNo;

    /**
     * 活动名称
     */
    @Column(name = "activity_name")
    private String activityName;

    /**
     * 开奖模式(0到时间开奖，1人数满了开奖，默认为0)
     */
    @Column(name = "lottery_type")
    private Byte lotteryType;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 开奖时间
     */
    @Column(name = "lottery_time")
    private Date lotteryTime;

    /**
     * 开奖人数(开奖模式的一种)
     */
    @Column(name = "lottery_num")
    private Integer lotteryNum;

    /**
     * 活动模式(0公开1私密，默认0)
     */
    @Column(name = "activity_type")
    private Byte activityType;

    /**
     * 房间密码
     */
    @Column(name = "room_password")
    private String roomPassword;

    /**
     * 奖品总数（金币）
     */
    @Column(name = "total_gold")
    private Double totalGold;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 活动状态0已结束1已开始2已创建4被禁止5关闭
     */
    @Column(name = "active_status")
    private Byte activeStatus;

    /**
     * 活动参与人数,默认为0
     */
    @Column(name = "people_num")
    private Integer peopleNum;

    /**
     * 是否有直播间(0没有1有 默认0)
     */
    @Column(name = "is_live_room")
    private Byte isLiveRoom;

    /**
     * 直播间url
     */
    @Column(name = "live_room_url")
    private String liveRoomUrl;

    /**
     * 房间描述
     */
    private String description;

    /**
     * 活动审核状态(0待审核,1通过,2不通过)
     */
    @Column(name = "verify_status")
    private Byte verifyStatus;

    /**
     * 活动点赞人数
     * @return
     */
    @Column(name = "total_upvote")
    private Integer totalUpvote;

    /**
     * 最大房间参与人数(活动模式为1人数满了开奖)
     * @return
     */
    @Column(name = "max_join_people")
    private Integer maxJoinPeople;

    /**
     * 活动剩余金额(活动可能被禁用或者其它原因停止)
     * @return
     */
    @Column(name = "remain_gold")
    private Double remainGold;

    /**
     * 开奖状态（0没开奖1开奖中2开奖完成默认0）
     */
    @Column(name = "lottery_status")
    private Byte lotteryStatus;

    /**
     * 获取房间号
     *
     * @return room_id - 房间号
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * 设置房间号
     *
     * @param roomId 房间号
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * 获取活动编号
     *
     * @return activity_no - 活动编号
     */
    public String getActivityNo() {
        return activityNo;
    }

    /**
     * 设置活动编号
     *
     * @param activityNo 活动编号
     */
    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    /**
     * 获取开奖模式
     *
     * @return lottery_type - 开奖模式
     */
    public Byte getLotteryType() {
        return lotteryType;
    }

    /**
     * 设置开奖模式
     *
     * @param lotteryType 开奖模式
     */
    public void setLotteryType(Byte lotteryType) {
        this.lotteryType = lotteryType;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取开奖时间
     *
     * @return lottery_time - 开奖时间
     */
    public Date getLotteryTime() {
        return lotteryTime;
    }

    /**
     * 设置开奖时间
     *
     * @param lotteryTime 开奖时间
     */
    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    /**
     * 获取开奖人数(开奖模式的一种)
     *
     * @return lottery_num - 开奖人数(开奖模式的一种)
     */
    public Integer getLotteryNum() {
        return lotteryNum;
    }

    /**
     * 设置开奖人数(开奖模式的一种)
     *
     * @param lotteryNum 开奖人数(开奖模式的一种)
     */
    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    /**
     * 获取活动模式
     *
     * @return activity_type - 活动模式
     */
    public Byte getActivityType() {
        return activityType;
    }

    /**
     * 设置活动模式
     *
     * @param activityType 活动模式
     */
    public void setActivityType(Byte activityType) {
        this.activityType = activityType;
    }

    /**
     * 获取房间密码
     *
     * @return room_password - 房间密码
     */
    public String getRoomPassword() {
        return roomPassword;
    }

    /**
     * 设置房间密码
     *
     * @param roomPassword 房间密码
     */
    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    /**
     * 获取奖品总数（金币）
     *
     * @return total_gold - 奖品总数（金币）
     */
    public Double getTotalGold() {
        return totalGold;
    }

    /**
     * 设置奖品总数（金币）
     *
     * @param totalGold 奖品总数（金币）
     */
    public void setTotalGold(Double totalGold) {
        this.totalGold = totalGold;
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

    /**
     * 获取活动状态0已结束1已开始
     *
     * @return active_status - 活动状态0已结束1已开始
     */
    public Byte getActiveStatus() {
        return activeStatus;
    }


    /**
     * 设置活动状态0已结束1已开始2已创建 默认为2
     *
     * @param activeStatus
     */
    public void setActiveStatus(Byte activeStatus) {
        this.activeStatus = activeStatus;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
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

    public Byte getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Byte verifyStatus) {
        this.verifyStatus = verifyStatus;
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

    public Double getRemainGold() {
        return remainGold;
    }

    public void setRemainGold(Double remainGold) {
        this.remainGold = remainGold;
    }

    public Byte getLotteryStatus() {
        return lotteryStatus;
    }

    public void setLotteryStatus(Byte lotteryStatus) {
        this.lotteryStatus = lotteryStatus;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}