package com.qibao.activity.service.impl;

import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityDetailVO;
import com.qibao.activity.entity.vo.ActivityVO;
import com.qibao.activity.enums.ActivityStatusEnum;
import com.qibao.activity.enums.LotteryStatusEnum;
import com.qibao.activity.enums.RoomStatusEnum;
import com.qibao.activity.enums.UserPrizeTypeEnum;
import com.qibao.activity.exception.RoomException;
import com.qibao.activity.feign.IUserFeign;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.activity.service.IRoomService;
import com.qibao.activity.service.IUserActivityService;
import com.qibao.activity.service.IUserPrizeService;
import com.qibao.activity.util.RoomHelp;
import com.qibao.common.contants.BaseExceptionMessage;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.ExceptionUtil;
import com.qibao.user.context.dto.UpdateGoldInfoDTO;
import com.qibao.user.context.vo.UserAccountVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class RoomActivityServiceImpl extends BaseService<RoomActivityEO> implements IRoomActivityService {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IUserPrizeService userPrizeService;

    @Autowired
    private IUserFeign userFeign;

    @Autowired
    private IUserActivityService userActivityService;


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

    @Override
    public List<ActivityVO> selectAllActivitys(Long roomId) {
        if (roomId == null){
            throw new RoomException(RoomException.ROOM_NOT_EXIT);
        }
        RoomActivityEO activityEO = new RoomActivityEO();
        activityEO.setRoomId(roomId);
        Example example = new Example(RoomActivityEO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roomId",roomId);
        Example.OrderBy orderBy = example.orderBy("startTime");
        orderBy.desc();
        List<RoomActivityEO> select = this.selectByExample(example);
        if (select != null && select.size() > 0){
            List<ActivityVO> activityVOS = BeanMapper.mapList(select, ActivityVO.class);
            if (activityVOS != null && activityVOS.size() > 0){
                for (ActivityVO activityVO : activityVOS){
                    activityVO.setTotalRmb(10d);    //TODO 这个从金币商城获取汇率然后修改
                }
            }
            return activityVOS;
        }
        return null;
    }

    @Override
    public boolean addActivity(RoomDTO roomDTO) {
        if (createActivityCondition(roomDTO)){
            if (roomDTO.getRoomId() == null){
                throw new RoomException(RoomException.ROOM_ID_EMPTY);
            }
            RoomEO roomEO = roomService.getRoomEO(roomDTO.getRoomId());

            if (roomEO.getRoomStatus() != null){
                if (RoomStatusEnum.FORBID.code() == roomEO.getRoomStatus()){
                    throw new RoomException(RoomException.ROOM_IS_SHUTDOWN);
                }
                if (RoomStatusEnum.ACTIVITY.code() == roomEO.getRoomStatus()) {
                    throw new RoomException(RoomException.ROOM_IS_USED);
                }
            }
            RoomActivityEO activityEO = selectCreateOrRunningActivity(roomDTO.getRoomId());
            if (activityEO != null){
                throw new RoomException(RoomException.ROOM_ACTIVITY_EXIT);
            }
            RoomEO room = BeanMapper.map(roomDTO, RoomEO.class);
            room.setRoomStatus(RoomStatusEnum.ACTIVITY.code());
            room.setLastUpdateTime(new Date());
            roomService.update(room);
            RoomActivityEO roomActivityEO = BeanMapper.map(roomDTO, RoomActivityEO.class);
            roomActivityEO.setActivityNo(RoomHelp.getActivityNo());
            roomActivityEO.setCreateTime(new Date());
            roomActivityEO.setStartTime(new Date());
            roomActivityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_CREATE.code());
            roomActivityEO.setRemainGold(roomDTO.getTotalGold());   //活动剩余金额即为活动金额
            roomActivityEO.setLotteryStatus(LotteryStatusEnum.LOTTERY_NO_START.code());
            Long activityId = this.insert(roomActivityEO);
            //记录创建信息
            roomDTO.setActivityId(activityId);
            updateActiviyUserInfo(roomDTO,2);
            return true;
        }
        return false;
    }

    /**
     * 更新用户创建房间数和记录创建房间消耗金币数
     */
    @Override
    public boolean updateActiviyUserInfo(RoomDTO roomDTO,int type) {
        UserPrizeEO prizeEO = new UserPrizeEO();
        prizeEO.setRelateId(roomDTO.getActivityId());
        prizeEO.setPrizeType(UserPrizeTypeEnum.USERPRIZETYPE4.getCode());
        prizeEO.setUserId(roomDTO.getUserId());
        prizeEO.setWinGold(roomDTO.getTotalGold());
        prizeEO.setCreateTime(new Date());
        Long prizeId = userPrizeService.insert(prizeEO);
        //用户金币减少
        BaseResponse<String> response = null;
        try {
            UpdateGoldInfoDTO dto = new UpdateGoldInfoDTO();
            dto.setType(2);
            dto.setGoldNum(roomDTO.getTotalGold());
            dto.setUserId(roomDTO.getUserId());
            dto.setDesp("创建房间消耗金币");
            response = userFeign.updateUserGoldInfos(dto);
        } catch (Exception e) {
            e.printStackTrace();
            //异常回滚,回滚完之后抛异常
            if (type == 1){ //创建房间
                roomService.deleteByPrimary(roomDTO.getRoomId());
                this.deleteByPrimary(roomDTO.getActivityId());
                userPrizeService.deleteByPrimary(prizeId);
            }else { //房间添加活动
                RoomEO roomEO = roomService.selectById(roomDTO.getRoomId());
                roomEO.setRoomStatus(RoomStatusEnum.NORMAL.code());
                roomEO.setLastUpdateTime(new Date());
                roomService.update(roomEO);
                this.deleteByPrimary(roomDTO.getActivityId());
                userPrizeService.deleteByPrimary(prizeId);
            }
            if (response != null){
                throw new RoomException(response.getMessage());
            }else {
                throw new RoomException(BaseExceptionMessage.SYSTEM_EXCEPTION);
            }

        }
        return true;
    }

    @Override
    public boolean createActivityCondition(RoomDTO roomDTO) {
        if (roomDTO == null || roomDTO.getUserId() == null){
            throw new RoomException(RoomException.USER_ID_EMPTY);
        }
        BaseResponse<UserAccountVO> userInfo = userFeign.getUserInfo(roomDTO.getUserId());
        UserAccountVO userAccountVO = userInfo.getResult();
        Double totalAmount = userAccountVO.getTotalAmount();    //用户总金币
        if (totalAmount < roomDTO.getTotalGold()){
            throw new RoomException(RoomException.USER_TOTAL_GOLD_LESS);
        }
        if (StringUtils.isEmpty(roomDTO.getRoomName())){
            throw new RoomException(RoomException.USER_ROOM_NAME_EMPTY);
        }
        if (roomDTO.getIsLiveRoom() == null || roomDTO.getIsLiveRoom() == 0){
            roomDTO.setIsLiveRoom((byte)0);
            roomDTO.setLiveRoomUrl(null);
        }else {
            roomDTO.setIsLiveRoom((byte)1);
            if (StringUtils.isEmpty(roomDTO.getLiveRoomUrl())){
                throw new  RoomException(RoomException.USER_LIVING_ROOM);
            }
        }
        if (roomDTO.getLotteryType() == null || roomDTO.getLotteryType() == 0){
            roomDTO.setLotteryType((byte)0);
            Date now = new Date();  //这个时间后台可配
            if (roomDTO.getLotteryTime() == null || (roomDTO.getLotteryTime().compareTo(now)==-1)){
                throw new  RoomException(RoomException.USER_LOTTY_TIME_ERROR);
            }
        }else {
            if (roomDTO.getMaxJoinPeople() == null || roomDTO.getMaxJoinPeople() == 0){
                throw new RoomException(RoomException.USER_JOIN_PEOPLE);
            }
            roomDTO.setLotteryType((byte)1);    //人数满了开奖
            roomDTO.setLotteryTime(null);
        }
        if (roomDTO.getActivityType() == null || roomDTO.getActivityType() == 0){
            roomDTO.setActivityType((byte)0);
            roomDTO.setRoomPassword(null);
        } else {
            if (StringUtils.isEmpty(roomDTO.getRoomPassword())){
                throw new RoomException(RoomException.USER_ROOM_PASSWORD);
            }
            roomDTO.setActivityType((byte)1);
        }
        return true;
    }

    @Override
    public boolean shutDownActivity(Long activityId) {
        if (activityId == null){
            throw new RoomException(RoomException.ROOM_ACTIVITY_ID_NULL);
        }
        RoomActivityEO activityEO = this.selectById(activityId);
        RoomEO roomEO = roomService.getRoomEO(activityEO.getRoomId());
        if (activityEO == null || roomEO == null){
            throw new RoomException(RoomException.ACTIVITY_ROOM_ERROR);
        }
        if (ActivityStatusEnum.ACTIVITY_CREATE.code() == activityEO.getActiveStatus()){ //关闭活动直接更新活动状态
            activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_SHUTDOWN.code());
            UpdateGoldInfoDTO dto = new UpdateGoldInfoDTO();
            dto.setUserId(roomEO.getUserId());
            dto.setGoldNum(activityEO.getTotalGold());
            dto.setType(1); //把用户创建活动的钱返回给用户
            try {
                userFeign.updateUserGoldInfos(dto);
                activityEO.setRemainGold(0d);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO  更新用户信息失败这个到时候统一自动任务回滚
            }
            update(activityEO);
            return true;
        }else {
            if (ActivityStatusEnum.ACTIVITY_RUN.code() == activityEO.getActiveStatus()){
                throw new RoomException(RoomException.ACTIVITY_SHUTDOWN_ERROR);
            }
            if (ActivityStatusEnum.ACTIVITY_RUN.code() == activityEO.getActiveStatus()){
                throw new RoomException(RoomException.ACTIVITY_RUNNING);
            }
            if (ActivityStatusEnum.ACTIVITY_END.code() == activityEO.getActiveStatus()){
                throw new RoomException(RoomException.ACTIVITY_END_ERROR);
            }
            if (ActivityStatusEnum.ACTIVITY_FORBID.code() == activityEO.getActiveStatus()){
                throw new RoomException(RoomException.ACTIVITY_FORBID_ERROR);
            }
            return false;
        }
    }

    @Override
    public ActivityDetailVO selectActivityInfo(Long activityId, Long userId) {
        if (activityId == null){
            throw new RoomException(RoomException.ACTIVITY_ID_EMPTY);
        }
        RoomActivityEO activityEO = selectById(activityId);
        if (activityEO == null){
            throw new RoomException(RoomException.ROOM_ACTIVITY_NOT_EXIT);
        }
        ActivityDetailVO result = BeanMapper.map(activityEO,ActivityDetailVO.class);
        result.setActivityId(activityEO.getId());
        try {
            RoomEO roomEO = roomService.getRoomEO(activityEO.getRoomId());
            UserAccountVO userAccountVO = userFeign.getUserInfo(roomEO.getUserId()).getResult();
            boolean joinActivity = userActivityService.isJoinActivity(userId, activityId);
            result.setUserId(roomEO.getUserId());   //房主
            result.setRoomName(roomEO.getRoomName());
            result.setRoomNo(roomEO.getRoomNo());
            result.setTotalRmb(10d);    //TODO 价值
            result.setUserImg(userAccountVO.getUserImg());
            result.setJoinActivity(joinActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
