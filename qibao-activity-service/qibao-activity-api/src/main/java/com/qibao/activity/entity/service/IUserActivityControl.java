package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.UserActivityDTO;
import com.qibao.activity.entity.vo.ActivityUserPrizeVO;
import com.qibao.activity.entity.vo.UserActivityVO;
import com.qibao.common.controller.IBaseController;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/userActivity")
public interface IUserActivityControl {

    /**
     * 5.立即参与房间活动接口
     * @return
     */
    @RequestMapping(value = "joinRoomActivity",method = RequestMethod.POST)
    public BaseResponse<Boolean> joinRoomActivity(@RequestBody UserActivityDTO userActivityDTO);

    /**
     * 6.获取活动用户信息列表
     * @return
     */
    @RequestMapping(value = "selectActivityUsers",method = RequestMethod.GET)
    public BaseResponse<UserActivityVO> selectActivityUsers(@RequestParam("activityId")Long activityId);

    /**
     * 13.房间活动中奖用户信息列表
     * @return
     */
    @RequestMapping(value = "userPrizeList",method = RequestMethod.GET)
    public BaseResponse<ActivityUserPrizeVO> userPrizeList(@RequestParam("activityId")Long activityId);
}
