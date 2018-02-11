package com.qibao.activity.backend.service;

import com.github.pagehelper.PageInfo;
import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.vo.BackendActivityVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IBackendActivityService extends IBaseService<RoomActivityEO> {

    /**
     * 修改活动
     * @param dto
     * @return
     */
    boolean updateActivity(BackendRoomActivityDTO dto);


    /**
     * 关闭活动
     * @param activityId
     * @return
     */
    boolean shutDownActivity(Long activityId);

    /**
     * 查询活动列表
     * @param dto
     * @return
     */
    PageInfo<BackendActivityVO> selectActivityList(BackendRoomActivityDTO dto);

    /**
     * 审核通过
     * @param activityId
     * @return
     */
    boolean verifyPass(Long activityId);

    /**
     * 审核不通过
     * @param activityId
     * @return
     */
    boolean verifyRefuse(Long activityId);

    /**
     * 根据房间号查询已创建或者正在开始的活动
     * @param roomId
     * @return
     */
    RoomActivityEO selectCreateOrRunningActivity(Long roomId);
}
