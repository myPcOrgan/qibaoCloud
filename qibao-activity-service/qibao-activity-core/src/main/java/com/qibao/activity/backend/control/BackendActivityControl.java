package com.qibao.activity.backend.control;

import com.github.pagehelper.PageInfo;
import com.qibao.activity.backend.service.IBackendActivityService;
import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.service.IBackendActivityControl;
import com.qibao.activity.entity.vo.BackendActivityVO;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("backend/activity")
public class BackendActivityControl extends BaseController implements IBackendActivityControl {

    @Autowired
    private IBackendActivityService activityService;

    /**
     * 修改活动
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateActivity", method = RequestMethod.POST)
    public BaseResponse<Boolean> updateActivity(@RequestBody BackendRoomActivityDTO dto){
        BaseResponse<Boolean> response = new BaseResponse<>();
        boolean result = activityService.updateActivity(dto);
        response.setResult(result);
        return response;
    }


    /**
     * 关闭活动
     * @param activityId
     * @return
     */
    @RequestMapping(value = "shutDownActivity", method = RequestMethod.GET)
    public BaseResponse<Boolean> shutDownActivity(@RequestParam("activityId") Long activityId){
        BaseResponse<Boolean> response = new BaseResponse<>();
        boolean result = activityService.shutDownActivity(activityId);
        response.setResult(result);
        return response;
    }

    /**
     * 审核通过
     * @param activityId
     * @return
     */
    @RequestMapping(value = "verifyPass", method = RequestMethod.GET)
    public BaseResponse<Boolean> verifyPass(@RequestParam("activityId") Long activityId){
        BaseResponse<Boolean> response = new BaseResponse<>();
        boolean result = activityService.verifyPass(activityId);
        response.setResult(result);
        return response;
    }

    /**
     * 审核不通过
     * @param activityId
     * @return
     */
    @RequestMapping(value = "verifyRefuse", method = RequestMethod.GET)
    public BaseResponse<Boolean> verifyRefuse(@RequestParam("activityId") Long activityId){
        BaseResponse<Boolean> response = new BaseResponse<>();
        boolean result = activityService.verifyRefuse(activityId);
        response.setResult(result);
        return response;
    }

    /**
     * 查询活动列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectActivityList", method = RequestMethod.POST)
    public BaseResponse<BackendActivityVO> selectActivityList(@RequestBody BackendRoomActivityDTO dto){
        BaseResponse<BackendActivityVO> response = new BaseResponse<>();
        PageInfo<BackendActivityVO> pageInfo = activityService.selectActivityList(dto);
        response.setData(pageInfo.getList());
        response.setTotalCount(pageInfo.getTotal());
        response.setPageIndex(dto.getPageIndex());
        response.setPageSize(dto.getPageSize());
        return response;
    }

}
