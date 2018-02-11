package com.qibao.activity.service.impl;

import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.UserActivityEO;
import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.dto.UserActivityDTO;
import com.qibao.activity.entity.vo.ActivityUserPrizeVO;
import com.qibao.activity.entity.vo.UserActivityVO;
import com.qibao.activity.enums.ActivityStatusEnum;
import com.qibao.activity.enums.ActivityVerifyEnum;
import com.qibao.activity.enums.LotteryStatusEnum;
import com.qibao.activity.enums.RoomStatusEnum;
import com.qibao.activity.exception.RoomException;
import com.qibao.activity.feign.IUserFeign;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.activity.service.IRoomService;
import com.qibao.activity.service.IUserActivityService;
import com.qibao.activity.service.IUserPrizeService;
import com.qibao.activity.util.RoomHelp;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.user.context.vo.UserAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserActivityServiceImpl extends BaseService<UserActivityEO> implements IUserActivityService {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomActivityService activityService;

    @Autowired
    private IUserFeign userFeign;

    @Autowired
    private IUserPrizeService userPrizeService;

    /**
     * 用户加入活动
     * @param dto
     * @return
     */
    @Override
    public boolean addActivity(UserActivityDTO dto) {
        if (dto == null || dto.getUserId() == null){
            throw new RoomException(RoomException.USER_ID_EMPTY);
        }
        if (dto.getRoomId() == null){
            throw new RoomException(RoomException.ROOM_ID_EMPTY);
        }
        RoomEO roomInfo = roomService.selectById(dto.getRoomId());
        if (roomInfo == null){
            throw new RoomException(RoomException.ROOM_NOT_EXIT);
        }

        if (roomInfo.getRoomStatus() != null){
            if (RoomStatusEnum.FORBID.code() == roomInfo.getRoomStatus()){
                throw new RoomException(RoomException.ROOM_IS_SHUTDOWN);
            }
        }
        RoomActivityEO activityEO = activityService.selectCreateOrRunningActivity(dto.getRoomId());
        if (activityEO == null){
            throw new RoomException(RoomException.ROOM_IS_NOT_START);
        }
        if (activityEO.getActivityType() == 1){ //私密活动判断密码是否正确
            if (dto.getYzm() == null){
                throw new RoomException(RoomException.ACTIVITY_YZM_ERROR);  //TODO 验证码错误，这个后面再加，现在只判断空
            }
            if(activityEO.getRoomPassword() != null && !(activityEO.getRoomPassword().equals(dto.getRoomPassword()))){
                throw new RoomException(RoomException.ACTIVITY_PASSWORD_ERROR);
            }
        }
        if (ActivityStatusEnum.ACTIVITY_END.code() == activityEO.getActiveStatus()){
            throw new RoomException(RoomException.ACTIVITY_END_ERROR);
        }
        if (ActivityStatusEnum.ACTIVITY_SHUTDOWN.code() == activityEO.getActiveStatus()){
            throw new RoomException(RoomException.ACTIVITY_SHUTDOWN_ERROR);
        }
        if (ActivityStatusEnum.ACTIVITY_FORBID.code() == activityEO.getActiveStatus()){
            throw new RoomException(RoomException.ACTIVITY_FORBID_ERROR);
        }
        if (ActivityStatusEnum.ACTIVITY_CREATE.code() == activityEO.getActiveStatus()){
            if (ActivityVerifyEnum.VERIFY_WAIT.code() == activityEO.getVerifyStatus()){
                throw new RoomException(RoomException.ROOM_VERIFY_WAIT);
            }
            if (ActivityVerifyEnum.VERIFY_FORBID.code() == activityEO.getVerifyStatus()){
                throw new RoomException(RoomException.ROOM_VERIFY_FAIL);
            }
            activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_RUN.code());
        }else {
            boolean join = isJoin(dto.getUserId(), activityEO.getId());
            if (join){
                throw new RoomException(RoomException.USER_EXIT_ACTIVITY);
            }
        }
        if (RoomStatusEnum.NORMAL.code() == roomInfo.getRoomStatus()){     //如果房间状态为可用，则更新房间状态为正在使用中
            roomInfo.setRoomStatus(RoomStatusEnum.ACTIVITY.code());
            roomInfo.setLastUpdateTime(new Date());
            roomService.update(roomInfo);
        }
        if (activityEO.getLotteryType() == 1){
            //判断人数是否已经满了
            if ((activityEO.getMaxJoinPeople() - activityEO.getPeopleNum()) == 1){  //活动的最后一个人,活动就结束,然后开奖
                activityEO.setPeopleNum(activityEO.getPeopleNum()+1);
                activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_END.code());
                activityService.update(activityEO);
                roomInfo.setRoomStatus(RoomStatusEnum.NORMAL.code());
                roomInfo.setLastUpdateTime(new Date());
                roomService.update(roomInfo);
                UserActivityEO userActivityEO = new UserActivityEO();
                userActivityEO.setActivityId(activityEO.getId());
                userActivityEO.setUserId(dto.getUserId());
                userActivityEO.setCreateTime(new Date());
                this.insert(userActivityEO);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        lotifyForMaxPeople(activityEO); //开奖
                    }
                });
                thread.start();
                LOGGER.info("人数满类型活动正在开奖",activityEO);
                return true;
            }else {
                updateUserActivityInfo(dto, activityEO);
                return true;
            }

        }else {
            Date now = new Date();
            if (activityEO.getLotteryTime().getTime() > now.getTime()){ //查看开奖时间是否过没
                updateUserActivityInfo(dto, activityEO);
                notifyLotify(activityEO); //通知开奖
                return true;
            }else {
                activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_END.code());
                roomInfo.setRoomStatus(RoomStatusEnum.NORMAL.code());
                roomInfo.setLastUpdateTime(new Date());
                activityService.update(activityEO);
                roomService.update(roomInfo);
                throw new RoomException(RoomException.ACTIVITY_END_ERROR);
            }

        }
    }

    protected void updateUserActivityInfo(UserActivityDTO dto, RoomActivityEO activityEO) {
        activityEO.setPeopleNum(activityEO.getPeopleNum()+1);
        activityService.update(activityEO);
        UserActivityEO userActivityEO = new UserActivityEO();
        userActivityEO.setActivityId(activityEO.getId());
        userActivityEO.setUserId(dto.getUserId());
        userActivityEO.setCreateTime(new Date());
        this.insert(userActivityEO);
    }


    @Override
    public List<UserActivityVO> getUserActivityInfo(Long activityId) {
        if (activityId == null){
            throw new RoomException(RoomException.ROOM_ACTIVITY_ID_NULL);
        }
        UserActivityEO userActivityEO = new UserActivityEO();
        userActivityEO.setActivityId(activityId);
        List<UserActivityEO> users = this.select(userActivityEO);
        if (users != null && users.size() > 0){
            List<Long> ids = new ArrayList<>();
            for (UserActivityEO user: users ){
                ids.add(user.getUserId());
            }
            BaseResponse<UserAccountVO> response = userFeign.getUserInfoByIds(ids);
            List<UserAccountVO> userInfos = response.getData();
            List<UserActivityVO> result = BeanMapper.mapList(userInfos,UserActivityVO.class);
            return result;
        }
        return null;
    }

    @Override
    public List<ActivityUserPrizeVO> userPrizeList(Long activity) {
        List<ActivityUserPrizeVO> list = null;
        UserPrizeEO userPrizeEO = new UserPrizeEO();
        userPrizeEO.setRelateId(activity);
        userPrizeEO.setPrizeType(1);
        List<UserPrizeEO> userPrizes = userPrizeService.select(userPrizeEO);
        if (userPrizes != null && userPrizes.size() > 0){
            list = new ArrayList<>();
            List<Long> ids = new ArrayList<>();
            for (int i = 0; i < userPrizes.size(); i++){
                ids.add(userPrizes.get(i).getUserId());
            }
            BaseResponse<UserAccountVO> response = userFeign.getUserInfoByIds(ids);
            List<UserAccountVO> userInfos = response.getData();
            for (UserPrizeEO userPrize : userPrizes){
                for (UserAccountVO userInfo: userInfos){
                    if (userPrize.getUserId().equals(userInfo.getId())){
                        ActivityUserPrizeVO activityVO = BeanMapper.map(userPrize,ActivityUserPrizeVO.class);
                        activityVO.setNickName(userInfo.getNickName());
                        activityVO.setUserImg(userInfo.getUserImg());
                        activityVO.setTotalRmb(0d); //这个值是设置的
                        list.add(activityVO);
                        break;
                    }
                }
            }
        }
        return list;
    }

    @Override
    public int getIJoinActivityCount(Long userId) {
        if (userId == null){
            throw new RoomException(RoomException.USER_ID_EMPTY);
        }
        UserActivityEO userActivityEO = new UserActivityEO();
        userActivityEO.setUserId(userId);
        List<UserActivityEO> select = this.select(userActivityEO);
        if (select != null){
            return select.size();
        }
        return 0;
    }

    /**
     * 通知开奖(倒计时开奖)
     */
    public void notifyLotify(RoomActivityEO activityEO) {
        if (activityEO.getActiveStatus() == ActivityStatusEnum.ACTIVITY_RUN.code()){    //只有正在进行还未开始开奖的活动才能进来开奖
            if (LotteryStatusEnum.LOTTERY_NO_START.code() == activityEO.getLotteryStatus()){
                LOGGER.info("通知开奖方法start");
                activityEO.setLotteryStatus(LotteryStatusEnum.LOTTERY_RUNNING.code());
                activityService.update(activityEO);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        lotty(activityEO);
                    }
                };
                timer.schedule(task,activityEO.getLotteryTime());   //TODO 这个有个问题，就是任务执行完了该线程不会结束
            }
        }

    }

    /**
     * 活动开奖(人数满了开奖)
     */
    public void lotifyForMaxPeople(RoomActivityEO activityEO) {
        if (activityEO.getActiveStatus() == ActivityStatusEnum.ACTIVITY_END.code()){    //只有活动先结束了才能开奖
            lotty(activityEO);
        }
    }

    @Override
    public boolean isJoinActivity(Long userId, Long activityId) {
        UserActivityEO userActivityEO = new UserActivityEO();
        userActivityEO.setActivityId(activityId);
        userActivityEO.setUserId(userId);
        UserActivityEO result = this.selectOne(userActivityEO);
        if (result != null){
            return true;
        }
        return false;
    }

    private void lotty(RoomActivityEO activityEO) {
        LOGGER.info("开奖方法已进来");
        activityEO = activityService.selectById(activityEO.getId());
        if (ActivityStatusEnum.ACTIVITY_FORBID.code() != activityEO.getActiveStatus() && LotteryStatusEnum.LOTTERY_END.code() != activityEO.getLotteryStatus()){ //活动有可能中途被禁止
            UserActivityEO userActivityEO = new UserActivityEO();
            userActivityEO.setActivityId(activityEO.getId());
            List<UserActivityEO> userActivityEOS = this.select(userActivityEO);
            if (userActivityEOS != null && userActivityEOS.size() > 0){
                List<Long> uids = new ArrayList<>();
                for (UserActivityEO user : userActivityEOS){
                    uids.add(user.getUserId());
                }
                List<UserPrizeEO> UserPrizes = RoomHelp.lotify(uids, activityEO.getTotalGold(), activityEO.getLotteryNum());
                for (UserPrizeEO userPrizeEO : UserPrizes){
                    userPrizeEO.setRelateId(activityEO.getId());
                    userPrizeEO.setPrizeType(1);
                    userPrizeService.insert(userPrizeEO);
                }
                activityEO.setRemainGold(0d);
                activityEO.setLotteryStatus(LotteryStatusEnum.LOTTERY_END.code());
                activityService.update(activityEO); //开奖之后活动剩余金额更新为0
                LOGGER.info("开奖完成",activityEO);
                LOGGER.info("-----------------------------lottery-end----------------------------------------------------------------");
            }
        } else {
            activityEO.setLotteryStatus(LotteryStatusEnum.LOTTERY_NO_START.code());
            activityService.update(activityEO); //开奖禁止后设置开奖状态为未开奖
        }

    }

    /**
     * 判断用户是否加入该活动
     * @param userId
     * @param activityId
     * @return
     */
    public boolean isJoin(Long userId, Long activityId){
        if (userId == null){
            throw new RoomException(RoomException.USER_ID_EMPTY);
        }
        if (activityId == null){
            throw new RoomException(RoomException.ROOM_ACTIVITY_ID_NULL);
        }
        UserActivityEO userActivityEO = new UserActivityEO();
        userActivityEO.setUserId(userId);
        userActivityEO.setActivityId(activityId);
        UserActivityEO result = this.selectOne(userActivityEO);
        if (result != null){
            return true;
        }
        return false;
    }


}
