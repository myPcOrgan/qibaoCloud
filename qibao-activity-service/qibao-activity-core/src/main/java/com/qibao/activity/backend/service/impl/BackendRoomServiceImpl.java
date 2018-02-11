package com.qibao.activity.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qibao.activity.backend.service.IBackendActivityService;
import com.qibao.activity.backend.service.IBackendRoomService;
import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.vo.BackendRoomVO;
import com.qibao.activity.enums.ActivityStatusEnum;
import com.qibao.activity.enums.RoomStatusEnum;
import com.qibao.activity.exception.RoomException;
import com.qibao.activity.mapper.RoomEOMapper;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.common.service.abs.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackendRoomServiceImpl extends BaseService<RoomEO> implements IBackendRoomService {

    @Autowired
    private RoomEOMapper roomEOMapper;

    @Autowired
    private IBackendActivityService activityService;

    @Override
    public boolean openRoom(Long roomId) {
        RoomEO roomEO = getRoomEO(roomId);
        if (RoomStatusEnum.FORBID.code() != roomEO.getRoomStatus()){
            throw new RoomException(RoomException.ROOM_OPEN_ERROR);
        }
        if (RoomStatusEnum.REFUSE.code() != roomEO.getRoomStatus()){
            throw new RoomException(RoomException.ROOM_REFULSE);
        }
        roomEO.setRoomStatus(RoomStatusEnum.NORMAL.code());
        roomEO.setLastUpdateTime(new Date());
        this.update(roomEO);
        return true;
    }

    @Override
    public boolean forbidRoom(Long roomId) {
        RoomEO roomEO = getRoomEO(roomId);
        if (RoomStatusEnum.FORBID.code() == roomEO.getRoomStatus()){
            return true;
        }
        if (RoomStatusEnum.REFUSE.code() == roomEO.getRoomStatus()){
            throw new RoomException(RoomException.ROOM_REFULSE);
        }
        forbidOrRefuseRoom(roomEO, RoomStatusEnum.FORBID.code());
        return true;
    }

    @Override
    public boolean refuseRoom(Long roomId) {
        RoomEO roomEO = getRoomEO(roomId);
        if (RoomStatusEnum.REFUSE.code() == roomEO.getRoomStatus()){
            return true;
        }
        forbidOrRefuseRoom(roomEO, RoomStatusEnum.REFUSE.code());
        return true;
    }

    @Override
    public PageInfo<BackendRoomVO> selectRoomList(BackendRoomActivityDTO dto) {
        Map map = createQueryMap(dto);
        PageHelper.startPage(dto.getPageIndex(),dto.getPageSize());
        List<BackendRoomVO> backendRoomVOS = roomEOMapper.selectBackendList(map);
        if (backendRoomVOS != null && backendRoomVOS.size() > 0){
            PageInfo<BackendRoomVO> list = new PageInfo<>(backendRoomVOS);
            return list;
        }
        return null;
    }

    /**
     * 禁用或者没收房间
     * @param roomEO
     * @param status  状态值
     */
    protected void forbidOrRefuseRoom(RoomEO roomEO, byte status) {
        RoomActivityEO activityEO = activityService.selectCreateOrRunningActivity(roomEO.getId());
        if (activityEO != null) {
            activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_FORBID.code());  //活动直接被禁止
            activityService.update(activityEO);
        }
        if (status == RoomStatusEnum.FORBID.code()){
            roomEO.setRoomStatus(RoomStatusEnum.FORBID.code());
        }
        if (status == RoomStatusEnum.REFUSE.code()){
            roomEO.setRoomStatus(RoomStatusEnum.REFUSE.code());
        }
        roomEO.setLastUpdateTime(new Date());
        update(roomEO);
    }

    protected RoomEO getRoomEO(Long roomId) {
        if (roomId == null){
            throw new RoomException(RoomException.ROOM_ID_EMPTY);
        }
        RoomEO roomEO = this.selectById(roomId);
        if (roomEO == null){
            throw new RoomException(RoomException.ROOM_NOT_EXIT);
        }
        return roomEO;
    }


    private Map createQueryMap(BackendRoomActivityDTO dto) {
        Map map = new HashMap();
        if (dto.getPageIndex() == null || dto.getPageIndex() <= 0){
            dto.setPageIndex(1);
        }
        if (dto.getPageSize() == null || dto.getPageSize() <= 0){
            dto.setPageSize(12);
        }
        return map;
    }
}
