package com.qibao.activity.backend.service;

import com.github.pagehelper.PageInfo;
import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.vo.BackendRoomVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

/**
 * 后端用的service（前后端分离）
 */
public interface IBackendRoomService  extends IBaseService<RoomEO> {

    /**
     * 启用房间
     * @param roomId
     * @return
     */
    boolean openRoom(Long roomId);

    /**
     * 禁用房间
     * @param roomId
     * @return
     */
    boolean forbidRoom(Long roomId);

    /**
     * 没收房间
     * @param roomId
     * @return
     */
    boolean refuseRoom(Long roomId);

    /**
     * 根据条件查询房间列表
     * @param dto
     * @return
     */
    PageInfo<BackendRoomVO> selectRoomList(BackendRoomActivityDTO dto);


}
