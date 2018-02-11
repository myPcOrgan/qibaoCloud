package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.service.IUserActivityControl;
import com.qibao.activity.entity.dto.UserActivityDTO;
import com.qibao.activity.entity.vo.ActivityUserPrizeVO;
import com.qibao.activity.entity.vo.UserActivityVO;
import com.qibao.activity.service.IUserActivityService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户活动参与表对应
 */
@RestController
@RequestMapping("/userActivity")
public class UserActivityControlImpl extends BaseController implements IUserActivityControl {

    @Autowired
    private IUserActivityService userActivityService;

    /**
     * 5.立即参与房间活动接口
     * @return
     */
    @RequestMapping(value = "joinRoomActivity",method = RequestMethod.POST)
    public BaseResponse<Boolean> joinRoomActivity(@RequestBody UserActivityDTO userActivityDTO){
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        boolean result = userActivityService.addActivity(userActivityDTO);
        baseResponse.setResult(result);
        return baseResponse;
    }

    /**
     * 6.获取活动用户信息列表
     * @return
     */
    @RequestMapping(value = "selectActivityUsers",method = RequestMethod.GET)
    public BaseResponse<UserActivityVO> selectActivityUsers(@RequestParam("activityId")Long activityId){
        BaseResponse<UserActivityVO> baseResponse = new BaseResponse<UserActivityVO>();
        List<UserActivityVO> result = userActivityService.getUserActivityInfo(activityId);
        baseResponse.setData(result);
        return baseResponse;
    }

    /**
     * 13.房间活动中奖用户信息列表
     * @return
     */
    @RequestMapping(value = "userPrizeList",method = RequestMethod.GET)
    public BaseResponse<ActivityUserPrizeVO> userPrizeList(@RequestParam("activityId")Long activityId){
        BaseResponse<ActivityUserPrizeVO> baseResponse = new BaseResponse<ActivityUserPrizeVO>();
        List<ActivityUserPrizeVO> list = userActivityService.userPrizeList(activityId);
        baseResponse.setData(list);
        return baseResponse;
    }



}
