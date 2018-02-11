package com.qibao.activity.entity.vo;

import java.util.Date;

/**
 * 后台房间列表
 */
public class BackendRoomVO {

    //房间id
    private Long roomId;
    //房间名
    private String roomName;
    //房间创建时间
    private Date createTime;
    //活动开始时间
    private Date startTime;
    //活动结束时间
    private Date endTime;
    //房间活动期数
    private Integer totalNum;
    //房间状态
    private Byte roomStatus;
    //房间总点赞数
    private Integer roomTotalUpvote;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Byte getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Byte roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getRoomTotalUpvote() {
        return roomTotalUpvote;
    }

    public void setRoomTotalUpvote(Integer roomTotalUpvote) {
        this.roomTotalUpvote = roomTotalUpvote;
    }
}
