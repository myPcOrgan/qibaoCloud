package com.qibao.activity.entity.vo;



import com.qibao.user.context.vo.UserAccountVO;

import java.util.Date;
import java.util.List;

public class RoomActivityVO {

    private Long id;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房间名称（默认用户昵称）
     */
    private String roomName;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 房间状态0禁用，1正常,2活动中,3被没收,默认1
     */
    private Byte roomStatus;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    /**
     * 房间类型，0用户创建，1官方创建,2主播,默认0
     */
    private Byte roomType;

    /**
     * 房间活动
     */
    private ActivityVO activity;

    /**
     * 房间活动列表
     */
    private List<ActivityVO> activityList;

    /**
     * 用户信息
     */
    private UserAccountVO userAccountVO;

    public UserAccountVO getUserAccountVO() {
        return userAccountVO;
    }

    public void setUserAccountVO(UserAccountVO userAccountVO) {
        this.userAccountVO = userAccountVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActivityVO getActivity() {
        return activity;
    }

    public void setActivity(ActivityVO activity) {
        this.activity = activity;
    }

    public List<ActivityVO> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityVO> activityList) {
        this.activityList = activityList;
    }

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

    /**
     * 获取房间状态0不可用，1可用，2正在使用中
     *
     * @return room_status - 房间状态0不可用，1可用，2正在使用中
     */
    public Byte getRoomStatus() {
        return roomStatus;
    }

    /**
     * 设置房间状态0不可用，1可用，2正在使用中
     *
     * @param roomStatus 房间状态0不可用，1可用，2正在使用中
     */
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
