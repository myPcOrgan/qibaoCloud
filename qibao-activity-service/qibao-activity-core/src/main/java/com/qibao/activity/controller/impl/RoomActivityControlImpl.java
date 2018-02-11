package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.service.IRoomActivityControl;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.ActivityVO;
import com.qibao.activity.entity.vo.RoomActivityVO;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房间活动表对应
 */
@RestController
@RequestMapping("/roomActivity")
public class RoomActivityControlImpl extends BaseController implements IRoomActivityControl {

    @Autowired
    private IRoomActivityService roomActivityService;

    /**
     * 10.房间新增活动
     * @return
     */
    @RequestMapping(value = "addActivity",method = RequestMethod.POST)
    public BaseResponse<Boolean> addActivity(@RequestBody RoomDTO roomDTO){
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        try {
            boolean result = roomActivityService.addActivity(roomDTO);
            baseResponse.setResult(result);
        } catch (BaseException e) {
            e.printStackTrace();
            baseResponse.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setErrorMessage(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 根据房间号查询房间活动列表
     * @param roomId
     * @return
     */
    @RequestMapping(value = "activityList", method = RequestMethod.GET)
    public BaseResponse<ActivityVO> selectActivityList(@RequestParam("roomId") Long roomId){
        BaseResponse<ActivityVO> baseResponse = new BaseResponse<ActivityVO>();
        try {
            List<ActivityVO> activityVOS = roomActivityService.selectAllActivitys(roomId);
            baseResponse.setData(activityVOS);
        } catch (BaseException e) {
            e.printStackTrace();
            baseResponse.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            e.printStackTrace();
            baseResponse.setErrorMessage(e.getMessage());
        }
        return baseResponse;

    }

    /**
     * 关闭活动
     * @param activityId    活动id
     * @return
     */
    @RequestMapping(value = "shutDownActivity", method = RequestMethod.GET)
    public BaseResponse<Boolean> shutDownActivity(@RequestParam("activityId") Long activityId){
        BaseResponse<Boolean> response = new BaseResponse<>();
        boolean result = roomActivityService.shutDownActivity(activityId);
        response.setResult(result);
        return response;
    }

    /**
     * 4.获取活动详情
     * @return
     */
    @RequestMapping(value = "selectActivityDetail",method = RequestMethod.GET)
    public BaseResponse<ActivityDetailVO> selectActivityDetail(@RequestParam("activityId") Long activityId, @RequestParam("userId") Long userId){
        BaseResponse<ActivityDetailVO> baseResponse = new BaseResponse<ActivityDetailVO>();
        ActivityDetailVO activityDetailVO = roomActivityService.selectActivityInfo(activityId, userId);
        baseResponse.setResult(activityDetailVO);
        return baseResponse;
    }


}
