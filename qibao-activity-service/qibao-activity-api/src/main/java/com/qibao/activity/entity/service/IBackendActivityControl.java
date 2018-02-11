package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.vo.BackendActivityVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("backend/activity")
public interface IBackendActivityControl {

    /**
     * 修改活动
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateActivity", method = RequestMethod.POST)
    BaseResponse<Boolean> updateActivity(@RequestBody BackendRoomActivityDTO dto);


    /**
     * 关闭活动
     * @param activityId
     * @return
     */
    @RequestMapping(value = "shutDownActivity", method = RequestMethod.GET)
    BaseResponse<Boolean> shutDownActivity(@RequestParam("activityId") Long activityId);

    /**
     * 审核通过
     * @param activityId
     * @return
     */
    @RequestMapping(value = "verifyPass", method = RequestMethod.GET)
    BaseResponse<Boolean> verifyPass(@RequestParam("activityId") Long activityId);

    /**
     * 审核不通过
     * @param activityId
     * @return
     */
    @RequestMapping(value = "verifyRefuse", method = RequestMethod.GET)
    BaseResponse<Boolean> verifyRefuse(@RequestParam("activityId") Long activityId);

    /**
     * 查询活动列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectActivityList", method = RequestMethod.POST)
    BaseResponse<BackendActivityVO> selectActivityList(@RequestBody BackendRoomActivityDTO dto);
}
