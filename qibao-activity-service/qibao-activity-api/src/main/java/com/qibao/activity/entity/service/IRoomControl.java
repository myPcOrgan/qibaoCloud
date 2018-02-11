package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.RoomListVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/room")
public interface IRoomControl {

    /**
     * 根据条件创建房间
     * @param roomDTO
     * @return
     */
    @RequestMapping(value = "createRoom",method = RequestMethod.POST)
    BaseResponse<Long> createRoom(@RequestBody RoomDTO roomDTO);

    /**
     * 根据房间号关闭房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "shutDownRoom",method = RequestMethod.GET)
    BaseResponse<Boolean> shutDownRoom(@RequestParam("roomId")  Long roomId);

    /**
     * 根据房间号编辑房间
     * @param roomDTO
     * @return
     */
    @RequestMapping(value = "editRoom",method = RequestMethod.POST)
    BaseResponse<Boolean> editRoom(@RequestBody RoomDTO roomDTO);

    /**
     * 根据房间号查询房间信息
     * @param roomId
     * @return
     */
    @RequestMapping(value = "selectRoom",method = RequestMethod.GET)
    BaseResponse<ActivityDetailVO> selectRoom(@RequestParam("roomId") Long roomId, @RequestParam("userId") Long userId);

    /**
     * 查询房间列表
     * @return
     */
    @RequestMapping(value = "selectRoomList",method = RequestMethod.POST)
    BaseResponse<RoomListVO> selectRoomList(@RequestBody RoomDTO roomDTO);
}
