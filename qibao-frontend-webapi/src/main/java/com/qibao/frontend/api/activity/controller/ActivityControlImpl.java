package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.ActivityVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IActivityFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roomActivity")
public class ActivityControlImpl {

    @Autowired
    private IActivityFeign activityFeign;

    /**
     * 10.房间新增活动
     * @return
     */
    @ApiOperation(value = "房间新增活动", notes = "房间新增活动")
    @RequestMapping(value = "addActivity",method = RequestMethod.POST)
    BaseResponse<Boolean> addActivity(@RequestBody RoomDTO roomDTO){
        Long userId = UserContext.getCurrentUserId();
        roomDTO.setUserId(userId);
        BaseResponse<Boolean> response = activityFeign.addActivity(roomDTO);
        return response;
    }

    /**
     * 根据房间号查询房间活动列表
     * @param roomId
     * @return
     */
    @ApiOperation(value = "根据房间号查询房间活动列表", notes = "根据房间号查询房间活动列表")
    @RequestMapping(value = "activityList", method = RequestMethod.GET)
    BaseResponse<ActivityVO> selectActivityList(@RequestParam("roomId") Long roomId){
        BaseResponse<ActivityVO> response = null;
        if (roomId == null){
            response = new BaseResponse<ActivityVO>();
            response.setErrorMessage("房间号不能为空！");
            return response;
        }
        response = activityFeign.selectActivityList(roomId);
        return response;
    }

    /**
     * 关闭房间活动
     * @param activityId
     * @return
     */
    @ApiOperation(value = "关闭活动", notes = "关闭活动")
    @RequestMapping(value = "shutDownActivity", method = RequestMethod.GET)
    BaseResponse<Boolean> shutDownActivity(@RequestParam("activityId") Long activityId){
        BaseResponse<Boolean> response = activityFeign.shutDownActivity(activityId);
        return response;
    }

    /**
     * 5.获取活动详情
     * @return
     */
    @ApiOperation(value = "获取活动详情", notes = "获取活动详情")
    @RequestMapping(value = "selectActivityDetail",method = RequestMethod.GET)
    public BaseResponse<ActivityDetailVO> selectActivityDetail(@RequestParam("activityId") Long activityId){
        Long userId = UserContext.getCurrentUserId();
        BaseResponse<ActivityDetailVO> response = activityFeign.selectActivityDetail(activityId, userId);
        return response;
    }
}
