package com.qibao.activity.service;

import com.github.pagehelper.PageInfo;
import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.RoomActivityVO;
import com.qibao.activity.entity.vo.RoomListVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IRoomService extends IBaseService<RoomEO> {

    /**
     * 创建房间，返回活动id
     * @param roomDTO
     */
    Long createRoom(RoomDTO roomDTO);

    /**
     * 根据房间号关闭房间,关闭房间就是把房间的状态改为0
     * @param roomId
     * @return
     */
    boolean shutDownRoom(Long roomId);

    boolean updateRoom(RoomDTO roomDTO);

    /**
     * 根据房间id和用户id获取房间信息
     * @param roomId
     * @param userId
     * @return
     */
    RoomActivityVO getRoomInfo(Long roomId,Long userId);

    /**
     * 获取房间列表信息(前端专用)
     * @return
     */
    PageInfo<RoomListVO> getRoomList(RoomDTO roomDTO);

    /**
     * 根据房间号获取房间信息，单表查询
     * @param roomId
     * @return
     */
    RoomEO getRoomEO(Long roomId);

    /**
     * 查询用户创建房间总数
     * @param userId
     * @return
     */
    int getUserCreateCount(Long userId);



}
