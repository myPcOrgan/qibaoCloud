package com.qibao.activity.service.impl;

import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.UpvoteEO;
import com.qibao.activity.exception.RoomException;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.activity.service.IUpvoteService;
import com.qibao.common.service.abs.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpvoteServiceImpl extends BaseService<UpvoteEO> implements IUpvoteService {

    @Autowired
    private IRoomActivityService roomActivityService;

    @Override
    public boolean addUpvote(Long roomId, Long userId) {
        RoomActivityEO activityEO = roomActivityService.selectCreateOrRunningActivity(roomId);
        if (activityEO == null){
            throw new RoomException(RoomException.ROOM_IS_NOT_START);
        }
        UpvoteEO upvoteEO = new UpvoteEO();
        upvoteEO.setUserId(userId);
        upvoteEO.setActivityId(activityEO.getId());
        UpvoteEO result = this.selectOne(upvoteEO);
        if (result != null){
            throw new RoomException(RoomException.ACTIVITY_IS_UPVOTE);
        }
        upvoteEO.setCreateTime(new Date());
        this.insert(upvoteEO);
        return true;
    }
}
