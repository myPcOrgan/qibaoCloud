package com.qibao.activity.mapper;

import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.vo.BackendRoomVO;
import com.qibao.activity.entity.vo.RoomActivityVO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface RoomEOMapper extends IBaseMapper<RoomEO> {

    /**
     * 根据条件查询房间开始的活动或即将开始的活动信息
     * @param map
     * @return
     */
    List<RoomActivityVO> selectRoomInfo(Map map);

    /**
     * 根据条件查询房间所有活动信息
     * @param map
     * @return
     */
    List<RoomActivityVO> selectRoomAllActivity(Map map);

    /**
     * 后端查询房间列表
     * @param map
     * @return
     */
    List<BackendRoomVO> selectBackendList(Map map);
}