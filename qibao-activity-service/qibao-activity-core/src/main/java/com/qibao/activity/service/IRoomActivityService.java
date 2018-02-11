package com.qibao.activity.service;

import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.ActivityVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IRoomActivityService extends IBaseService<RoomActivityEO> {

    /**
     * 根据房间号查询已创建或者正在开始的活动
     * @param roomId
     * @return
     */
    RoomActivityEO selectCreateOrRunningActivity(Long roomId);

    /**
     * 根据房间id查询该房间的所有活动
     * @param roomId
     * @return
     */
    List<ActivityVO> selectAllActivitys(Long roomId);

    /**
     * 房间新增活动
     * @param roomDTO
     * @return
     */
    boolean addActivity(RoomDTO roomDTO);


    /**
     * 用户新增活动时更新活动用户信息
     * @param roomDTO
     * @param type    类型1创建房间活动2房间添加活动
     * @return
     */
    boolean updateActiviyUserInfo(RoomDTO roomDTO,int type);

    /**
     * 创建活动条件
     * @param roomDTO
     * @return
     */
    boolean createActivityCondition(RoomDTO roomDTO);

    /**
     * 根据房间id关闭活动，只有活动状态在已创建的时候才能关闭
     * @param activityId
     * @return
     */
    boolean shutDownActivity(Long activityId);

    /**
     * 查询活动详情
     * @param activityId
     * @param userId 当前用id
     * @return
     */
    ActivityDetailVO selectActivityInfo(Long activityId,Long userId);

}
