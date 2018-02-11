package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.vo.BackendRoomVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("backend/room")
public interface IBackendRoomControl {

    /**
     * 启用房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "openRoom",method = RequestMethod.GET)
    BaseResponse<Boolean> openRoom(@RequestParam("roomId") Long roomId);

    /**
     * 禁用房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "forbidRoom",method = RequestMethod.GET)
    BaseResponse<Boolean> forbidRoom(@RequestParam("roomId") Long roomId);

    /**
     * 没收房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "refuseRoom",method = RequestMethod.GET)
    BaseResponse<Boolean> refuseRoom(@RequestParam("roomId") Long roomId);

    /**
     * 根据条件查询房间列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectRoomList",method = RequestMethod.POST)
    BaseResponse<BackendRoomVO> selectRoomList(@RequestBody BackendRoomActivityDTO dto);
}
