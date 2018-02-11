package com.qibao.activity.controller.impl;

import com.github.pagehelper.PageInfo;
import com.qibao.activity.entity.service.IRoomControl;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.RoomActivityVO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.RoomListVO;
import com.qibao.activity.service.IRoomService;
import com.qibao.activity.service.IUserActivityService;
import com.qibao.activity.util.RoomHelp;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 房间表对应
 */
@RestController
@RequestMapping("/room")
public class RoomControlImpl extends BaseController implements IRoomControl {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IUserActivityService userActivityService;

    /**
     * 1.创建房间
     * @return
     */
    @RequestMapping(value = "createRoom",method = RequestMethod.POST)
    public BaseResponse<Long> createRoom(@RequestBody RoomDTO roomDTO){
        LOGGER.info("创建房间请求参数:{}",RoomHelp.toJsonString(roomDTO));
        BaseResponse<Long> baseResponse = new BaseResponse<Long>();
        Long roomId = roomService.createRoom(roomDTO);
        baseResponse.setResult(roomId);
        return baseResponse;
    }

    /**
     *2.关闭房间
     * @return
     */
    @RequestMapping(value = "shutDownRoom",method = RequestMethod.GET)
    public BaseResponse<Boolean> shutDownRoom(@RequestParam("roomId") Long roomId){
        BaseResponse baseResponse = new BaseResponse();
        boolean result = roomService.shutDownRoom(roomId);
        baseResponse.setResult(result);
        return baseResponse;
    }

    /**
     * 3.编辑房间
     * @return
     */
    @RequestMapping(value = "editRoom",method = RequestMethod.POST)
    public BaseResponse<Boolean> editRoom(@RequestBody RoomDTO roomDTO){
        BaseResponse<Boolean> baseResponse = new BaseResponse<Boolean>();
        boolean result = roomService.updateRoom(roomDTO);
        baseResponse.setResult(result);
        return baseResponse;
    }

    /**
     * 4.前端获取房间信息
     * @return
     */
    @RequestMapping(value = "selectRoom",method = RequestMethod.GET)
    public BaseResponse<ActivityDetailVO> selectRoom(@RequestParam("roomId") Long roomId, @RequestParam("userId") Long userId){
        BaseResponse<ActivityDetailVO> baseResponse = new BaseResponse<ActivityDetailVO>();
        RoomActivityVO roomInfo = roomService.getRoomInfo(roomId,userId);
        if (roomInfo != null){
            ActivityDetailVO activityDetailVO = BeanMapper.map(roomInfo.getActivityList().get(0),ActivityDetailVO.class);
            activityDetailVO.setActivityId(roomInfo.getActivityList().get(0).getId());
            activityDetailVO.setRoomName(roomInfo.getRoomName());
            activityDetailVO.setUserImg(roomInfo.getUserAccountVO().getUserImg());
            activityDetailVO.setUserId(roomInfo.getUserId());
            activityDetailVO.setRoomNo(roomInfo.getRoomNo());
            activityDetailVO.setJoinActivity(userActivityService.isJoinActivity(userId, activityDetailVO.getActivityId()));
            baseResponse.setResult(activityDetailVO);
        }
        return baseResponse;
    }

    /**
     * 7.前端获取房间列表信息(包括我参与的和我创建的)
     * @return
     */
    @RequestMapping(value = "selectRoomList",method = RequestMethod.POST)
    public BaseResponse<RoomListVO> selectRoomList(@RequestBody RoomDTO roomDTO){
        LOGGER.info("房间列表请求参数:{}",RoomHelp.toJsonString(roomDTO));
        BaseResponse<RoomListVO> baseResponse = new BaseResponse<RoomListVO>();
        PageInfo<RoomListVO> roomList = roomService.getRoomList(roomDTO);
        baseResponse.setData(roomList.getList());
        baseResponse.setTotalCount(roomList.getTotal());
        baseResponse.setPageIndex(roomDTO.getPageIndex());
        baseResponse.setPageSize(roomDTO.getPageSize());
        return baseResponse;
    }

}
