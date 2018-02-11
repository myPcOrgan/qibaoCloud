package com.qibao.activity.entity.dto;

public class UserActivityDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房间id
     */
    private Long roomId;

    /**
     * 活动密码
     */
    private String roomPassword;

    /**
     * 验证码
     * @return
     */
    private String yzm;

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

    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }
}
