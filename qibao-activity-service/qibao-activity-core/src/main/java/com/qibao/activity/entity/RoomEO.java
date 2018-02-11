package com.qibao.activity.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_room")
public class RoomEO extends BaseEntity {

    /**
     * 房间号
     */
    @Column(name = "room_no")
    private String roomNo;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 房间名称（默认用户昵称）
     */
    @Column(name = "room_name")
    private String roomName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 房间状态0禁用，1正常,2活动中,3被没收,默认1
     */
    @Column(name = "room_status")
    private Byte roomStatus;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 最后修改时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 房间类型，0用户创建，1官方创建,2主播,默认0
     */
    @Column(name = "room_type")
    private Byte roomType;

    /**
     * 获取房间号
     *
     * @return room_no - 房间号
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * 设置房间号
     *
     * @param roomNo 房间号
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取房间名称（默认用户昵称）
     *
     * @return room_name - 房间名称（默认用户昵称）
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 设置房间名称（默认用户昵称）
     *
     * @param roomName 房间名称（默认用户昵称）
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public Byte getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Byte roomStatus) {
        this.roomStatus = roomStatus;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取最后修改时间
     *
     * @return lastUpdateTime - 最后修改时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastUpdateTime 最后修改时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Byte getRoomType() {
        return roomType;
    }

    public void setRoomType(Byte roomType) {
        this.roomType = roomType;
    }
}