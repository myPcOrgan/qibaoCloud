package com.qibao.activity.backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qibao.activity.backend.service.IBackendActivityService;
import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.BackendActivityVO;
import com.qibao.activity.entity.vo.BackendRoomVO;
import com.qibao.activity.enums.ActivityStatusEnum;
import com.qibao.activity.enums.ActivityVerifyEnum;
import com.qibao.activity.exception.RoomException;
import com.qibao.activity.mapper.RoomEOMapper;
import com.qibao.activity.service.IRoomService;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackendActivityServiceImpl extends BaseService<RoomActivityEO> implements IBackendActivityService {

    @Autowired
    private IRoomService roomService;

    @Override
    public boolean updateActivity(BackendRoomActivityDTO dto) {
        RoomDTO roomDTO = BeanMapper.map(dto, RoomDTO.class);   //这个直接调用前端用的编辑活动
        return roomService.updateRoom(roomDTO);
    }

    @Override
    public boolean shutDownActivity(Long activityId) {  //关闭活动及禁止活动
        RoomActivityEO activityEO = getRoomActivityEO(activityId);
        activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_FORBID.code());
        activityEO.setEndTime(new Date());  //活动结束
        update(activityEO);
        return false;
    }

    @Override
    public PageInfo<BackendActivityVO> selectActivityList(BackendRoomActivityDTO dto) {
        RoomActivityEO activityEO = BeanMapper.map(dto, RoomActivityEO.class);
        List<RoomActivityEO> list = this.select(activityEO);
        if (list != null && list.size() > 0){
            List<BackendActivityVO> backendActivityVOS = BeanMapper.mapList(list, BackendActivityVO.class);
            PageInfo<BackendActivityVO> result = new PageInfo<>(backendActivityVOS);
            return result;
        }
        return null;
    }


    @Override
    public boolean verifyPass(Long activityId) {
        RoomActivityEO activityEO = getRoomActivityEO(activityId);
        activityEO.setVerifyStatus(ActivityVerifyEnum.VERIFY_PASS.code());
        this.update(activityEO);
        return false;
    }



    @Override
    public boolean verifyRefuse(Long activityId) {
        RoomActivityEO activityEO = getRoomActivityEO(activityId);
        if (ActivityVerifyEnum.VERIFY_PASS.code() != activityEO.getVerifyStatus()){ //待审核的直接更新
            activityEO.setVerifyStatus(ActivityVerifyEnum.VERIFY_FORBID.code());
            this.update(activityEO);
            return true;
        }else { //之前审核通过，现在又审核不通过，直接抛异常
            throw new RoomException(RoomException.ROOM_VERIFY_ERROR);
        }
    }

    @Override
    public RoomActivityEO selectCreateOrRunningActivity(Long roomId) {
        if (roomId == null){
            throw new RoomException(RoomException.ROOM_NOT_EXIT);
        }
        RoomActivityEO activityEO = new RoomActivityEO();
        activityEO.setRoomId(roomId);
        activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_RUN.code());    //先查询已开始的活动，如果没有再查询已创建的活动
        RoomActivityEO result = this.selectOne(activityEO);
        if (result == null){
            activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_CREATE.code());
            result = this.selectOne(activityEO);
        }
        return result;
    }

    protected RoomActivityEO getRoomActivityEO(Long activityId) {
        if (activityId == null){
            throw new RoomException(RoomException.ROOM_ACTIVITY_ID_NULL);
        }
        RoomActivityEO activityEO = this.selectById(activityId);
        if (activityEO == null){
            throw new RoomException(RoomException.ROOM_ACTIVITY_NOT_EXIT);
        }
        return activityEO;
    }
}
