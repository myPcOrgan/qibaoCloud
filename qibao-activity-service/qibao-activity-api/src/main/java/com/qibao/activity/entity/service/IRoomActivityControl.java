package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.ActivityVO;
import com.qibao.common.controller.IBaseController;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/roomActivity")
public interface IRoomActivityControl {

    /**
     * 10.房间新增活动
     * @return
     */
    @RequestMapping(value = "addActivity",method = RequestMethod.POST)
    BaseResponse<Boolean> addActivity(@RequestBody RoomDTO roomDTO);

    /**
     * 根据房间号查询房间活动列表
     * @param roomId
     * @return
     */
    @RequestMapping(value = "activityList", method = RequestMethod.GET)
    BaseResponse<ActivityVO> selectActivityList(@RequestParam("roomId") Long roomId);

    /**
     * 关闭活动
     * @param activityId    活动id
     * @return
     */
    @RequestMapping(value = "shutDownActivity", method = RequestMethod.GET)
    public BaseResponse<Boolean> shutDownActivity(@RequestParam("activityId") Long activityId);

    /**
     * 4.获取活动详情
     * @return
     */
    @RequestMapping(value = "selectActivityDetail",method = RequestMethod.GET)
    public BaseResponse<ActivityDetailVO> selectActivityDetail(@RequestParam("activityId") Long activityId, @RequestParam("userId") Long userId);
}
