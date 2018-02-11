package com.qibao.activity.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_user_activity")
public class UserActivityEO extends BaseEntity {

    /**
     * 活动id
     */
    @Column(name = "activity_id")
    private Long activityId;

    /**
     * 用户帐号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取活动id
     *
     * @return activity_id - 活动id
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * 设置活动id
     *
     * @param activityId 活动id
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取用户帐号
     *
     * @return user_id - 用户帐号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户帐号
     *
     * @param userId 用户帐号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
}