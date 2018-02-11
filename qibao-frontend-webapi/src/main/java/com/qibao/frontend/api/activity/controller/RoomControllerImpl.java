package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.RoomListVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IRoomFeign;
import com.qibao.frontend.feign.IUserInfoFeign;
import com.qibao.user.context.vo.UserAccountVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/room")
public class RoomControllerImpl {

    @Autowired
    private IRoomFeign roomFeign;

    @Autowired
    private IUserInfoFeign userInfoFeign;

    /**
     * 根据条件创建房间
     * @param roomDTO
     * @return
     */
    @ApiOperation(value = "创建房间", notes = "创建房间")
    @RequestMapping(value = "createRoom", method = RequestMethod.POST)
    BaseResponse<Long> createRoom(@RequestBody RoomDTO roomDTO){
        Long userId = UserContext.getCurrentUserId();
        roomDTO.setUserId(userId);
        BaseResponse<Long> response = roomFeign.createRoom(roomDTO);
        return response;
    }

    /**
     * 根据房间号关闭房间
     * @param roomId
     * @return
     */
    @ApiOperation(value = "关闭房间", notes = "关闭房间")
    @RequestMapping(value = "shutDownRoom", method = RequestMethod.GET)
    BaseResponse<Boolean> shutDownRoom(@RequestParam("roomId") Long roomId){
        BaseResponse<Boolean> response = roomFeign.shutDownRoom(roomId);
        return response;
    }

    /**
     * 根据房间号编辑房间
     * @param roomDTO
     * @return
     */
    @ApiOperation(value = "编辑房间", notes = "编辑房间")
    @RequestMapping(value = "editRoom", method = RequestMethod.POST)
    BaseResponse<Boolean> editRoom(@RequestBody RoomDTO roomDTO){
        Long currentUserId = UserContext.getCurrentUserId();
        roomDTO.setUserId(currentUserId);
        BaseResponse<Boolean> response = roomFeign.editRoom(roomDTO);
        return response;
    }

    /**
     * 根据房间号查询房间信息
     * @param roomId
     * @return
     */
    @ApiOperation(value = "查询房间信息", notes = "查询房间信息")
    @RequestMapping(value = "selectRoom", method = RequestMethod.GET)
    BaseResponse<ActivityDetailVO> selectRoom(@RequestParam("roomId")Long roomId){
        Long currentUserId = UserContext.getCurrentUserId();
        BaseResponse<UserAccountVO> userInfo = userInfoFeign.getUserInfo(currentUserId);
        UserContext.setCurrentUser(userInfo.getResult());
        BaseResponse<ActivityDetailVO> response = roomFeign.selectRoom(roomId, currentUserId);
        return response;
    }

    /**
     * 查询房间列表(包含我参与的和我创建的)
     * @return
     */
    @ApiOperation(value = "查询房间列表", notes = "查询房间列表")
    @RequestMapping(value = "selectRoomList", method = RequestMethod.POST)
    BaseResponse<RoomListVO> selectRoomList(@RequestBody RoomDTO roomDTO){
        if (roomDTO != null && roomDTO.getRequestType() != 0){
            Long currentUserId = UserContext.getCurrentUserId();
            if (currentUserId == null){
                throw new BaseException(1,"请登录!");
            }
            roomDTO.setUserId(currentUserId);
        }
        BaseResponse<RoomListVO> response = roomFeign.selectRoomList(roomDTO);
        return response;
    }
}
