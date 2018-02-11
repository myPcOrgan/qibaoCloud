package com.qibao.activity.service;

import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.UserActivityEO;
import com.qibao.activity.entity.dto.UserActivityDTO;
import com.qibao.activity.entity.vo.ActivityUserPrizeVO;
import com.qibao.activity.entity.vo.UserActivityVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IUserActivityService extends IBaseService<UserActivityEO> {

    /**
     * 加入活动
     * @param userActivityDTO
     * @return
     */
    boolean addActivity(UserActivityDTO userActivityDTO);

    /**
     * 根据活动id获取用户信息
     * @param activity
     * @return
     */
    List<UserActivityVO> getUserActivityInfo(Long activity);

    /**
     * 活动中奖用户信息列表
     * @param activity
     * @return
     */
    List<ActivityUserPrizeVO> userPrizeList(Long activity);

    /**
     * 查询我参加的房间活动总数
     * @param userId
     * @return
     */
    int getIJoinActivityCount(Long userId);

    /**
     * 倒计时开奖
     * @param activityEO
     */
    void notifyLotify(RoomActivityEO activityEO);

    /**
     * 人数满了开奖
     * @param activityEO
     */
    void lotifyForMaxPeople(RoomActivityEO activityEO);

    /**
     * 用户是否参与该活动
     * @param userId
     * @param activityId
     * @return
     */
    boolean isJoinActivity(Long userId,Long activityId);
}
