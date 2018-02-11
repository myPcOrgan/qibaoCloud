package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.dto.UserActivityDTO;
import com.qibao.activity.entity.vo.ActivityUserPrizeVO;
import com.qibao.activity.entity.vo.UserActivityVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IUserActivityFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/userActivity")
public class UserActivityControllImpl {

    @Autowired
    private IUserActivityFeign userActivityFeign;

    /**
     * 5.立即参与房间活动接口
     * @return
     */
    @ApiOperation(value = "立即参与房间活动接口", notes = "立即参与房间活动接口")
    @RequestMapping(value = "joinRoomActivity",method = RequestMethod.POST)
    public BaseResponse<Boolean> joinRoomActivity(@RequestBody UserActivityDTO userActivityDTO){
        Long userId = UserContext.getCurrentUserId();
        userActivityDTO.setUserId(userId);
        BaseResponse<Boolean> response = userActivityFeign.joinRoomActivity(userActivityDTO);
        return response;
    }

    /**
     * 6.获取活动用户信息列表
     * @return
     */
    @ApiOperation(value = "获取活动用户信息列表", notes = "获取活动用户信息列表")
    @RequestMapping(value = "selectActivityUsers",method = RequestMethod.GET)
    public BaseResponse<UserActivityVO> selectActivityUsers(@RequestParam("activityId")Long activityId){
        BaseResponse<UserActivityVO> response = userActivityFeign.selectActivityUsers(activityId);
        return response;
    }

    /**
     * 13.房间活动中奖用户信息列表
     * @return
     */
    @ApiOperation(value = "房间活动中奖用户信息列表", notes = "房间活动中奖用户信息列表")
    @RequestMapping(value = "userPrizeList",method = RequestMethod.GET)
    public BaseResponse<ActivityUserPrizeVO> userPrizeList(@RequestParam("activityId")Long activityId){
        BaseResponse<ActivityUserPrizeVO> response = userActivityFeign.userPrizeList(activityId);
        return response;
    }

    /**
     * 获取服务器当前时间
     * @return
     */
    @ApiOperation(value = "获取服务器当前时间", notes = "获取服务器当前时间")
    @RequestMapping(value = "serverCurrentTime",method = RequestMethod.GET)
    public BaseResponse<Long> serverCurrentTime(){
        long time = new Date().getTime();
        BaseResponse<Long> response = new BaseResponse<>();
        response.setResult(time);
        return response;
    }
}
