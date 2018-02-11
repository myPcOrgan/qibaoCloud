package com.qibao.activity.entity.vo;

import java.util.Date;

public class ActivityVO {

    /**
     * 活动id
     */
    private Long id;

    /**
     * 房间号
     */
    private Long roomId;

    /**
     * 活动编号
     */
    private String activityNo;

    /**
     * 开奖模式
     */
    private Byte lotteryType;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 开奖时间
     */
    private Date lotteryTime;

    /**
     * 开奖人数(开奖模式的一种)
     */
    private Integer lotteryNum;

    /**
     * 活动模式
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 活动状态0已结束1已开始
     */
    private Long activeStatus;

    /**
     * 房间参与人数
     * @return
     */
    private Integer peopleNum;

    /**
     * 是否有直播间
     */
    private Byte isLiveRoom;

    /**
     * 直播间url
     */
    private String liveRoomUrl;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动审核状态(0待审核,1通过,2不通过)
     */
    private Byte verifyStatus;

    /**
     * 活动点赞人数
     */
    private Integer totalUpvote;

    /**
     * 最大房间参与人数(活动模式为1人数满了开奖)
     * @return
     */
    private Integer maxJoinPeople;

    //总价值
    private Double totalRmb;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    public Long getActiveStatus() {
        return activeStatus;
    }

    /**
     * 设置活动状态0已结束1已开始
     *
     * @param activeStatus 活动状态0已结束1已开始
     */
    public void setActiveStatus(Long activeStatus) {
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

    public Double getTotalRmb() {
        return totalRmb;
    }

    public void setTotalRmb(Double totalRmb) {
        this.totalRmb = totalRmb;
    }
}
