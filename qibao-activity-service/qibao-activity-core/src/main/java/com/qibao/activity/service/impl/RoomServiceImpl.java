package com.qibao.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qibao.activity.entity.RoomActivityEO;
import com.qibao.activity.entity.RoomEO;
import com.qibao.activity.entity.UserActivityEO;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.ActivityVO;
import com.qibao.activity.entity.vo.RoomActivityVO;
import com.qibao.activity.entity.vo.RoomListVO;
import com.qibao.activity.enums.*;
import com.qibao.activity.exception.RoomException;
import com.qibao.activity.feign.IUserFeign;
import com.qibao.activity.mapper.RoomEOMapper;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.activity.service.IRoomService;
import com.qibao.activity.service.IUserActivityService;
import com.qibao.activity.util.RoomHelp;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.ExceptionUtil;
import com.qibao.user.context.vo.UserAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServiceImpl extends BaseService<RoomEO> implements IRoomService {

    @Autowired
    private IRoomActivityService activityService;

    @Autowired
    private RoomEOMapper roomEOMapper;

    @Autowired
    private IUserActivityService userActivityService;

    @Autowired
    private IUserFeign userFeign;

    @Override
    public Long createRoom(RoomDTO roomDTO) {
        LOGGER.info("创建房间方法start");
        if (createRoomCondition(roomDTO)){
            LOGGER.info("创建房间条件通过");
            RoomEO room = BeanMapper.map(roomDTO,RoomEO.class);
            RoomActivityEO activityEO = BeanMapper.map(roomDTO,RoomActivityEO.class);
            UserAccountVO userInfo = getUserInfo(roomDTO.getUserId());
            if (userInfo.getUserGrade() != null && userInfo.getUserGrade() == 1){
                room.setRoomType((byte)1);  //官方
            }else {
                room.setRoomType((byte)0);  //用户
            }
            Long [] ids = createRoomActivity(room, activityEO);
            roomDTO.setActivityId(ids[1]);
            roomDTO.setRoomId(ids[0]);
            activityService.updateActiviyUserInfo(roomDTO,1);
            return ids[1];
        }
        return null;
    }



    @Override
    public boolean shutDownRoom(Long roomId) {
        RoomEO roomEO = getRoomEO(roomId);
        if (roomEO.getRoomStatus() != null){
            if (RoomStatusEnum.ACTIVITY.code() == roomEO.getRoomStatus()){
                throw new RoomException(RoomException.ROOM_IS_USED);
            }
        }
        roomEO.setRoomStatus(RoomStatusEnum.FORBID.code());
        roomEO.setLastUpdateTime(new Date());
        this.update(roomEO);
        return true;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        RoomEO roomEO = this.getRoomEO(roomDTO.getRoomId());
        if (roomEO.getRoomStatus() != null){
            if (RoomStatusEnum.FORBID.code() == roomEO.getRoomStatus()){
                throw new RoomException(RoomException.ROOM_IS_SHUTDOWN);
            }
            if (RoomStatusEnum.ACTIVITY.code() == roomEO.getRoomStatus()){
                throw new RoomException(RoomException.ROOM_IS_USED);
            }
        }
        RoomActivityEO activityEO = new RoomActivityEO();
        activityEO.setRoomId(roomEO.getId());
        activityEO.setActiveStatus((byte)2);    //这里查询已创建的活动
        RoomActivityEO activity = activityService.selectOne(activityEO);
        if (activity == null || activity.getId() == null){
            throw new RoomException(RoomException.ROOM_IS_NOT_START);
        }
        if (createRoomCondition(roomDTO)){
            RoomEO updateRoom = BeanMapper.map(roomDTO,RoomEO.class);
            RoomActivityEO updateActivity = BeanMapper.map(roomDTO,RoomActivityEO.class);
            updateRoom.setId(roomEO.getId());
            updateRoom.setLastUpdateTime(new Date());
            updateActivity.setId(activity.getId());
            this.update(updateRoom);
            activityService.update(updateActivity);
            return true;
        }
        return false;

    }

    @Override
    public RoomActivityVO getRoomInfo(Long roomId,Long userId) {
        if (roomId == null){
            throw new RoomException(RoomException.ROOM_ID_EMPTY);
        }
        RoomDTO dto = new RoomDTO();
        dto.setRoomId(roomId);
        Map map = queryMap(dto);
        List<RoomActivityVO> roomActivityVOS = roomEOMapper.selectRoomInfo(map);
        if (roomActivityVOS != null && roomActivityVOS.size() > 0){
            RoomActivityVO roomActivityVO = roomActivityVOS.get(0);
            UserAccountVO userInfo = getUserInfo(userId);
            roomActivityVO.setUserAccountVO(userInfo);  //设置用户信息
            return roomActivityVO;
        }
        return null;
    }

    @Override
    public PageInfo<RoomListVO> getRoomList(RoomDTO roomDTO) {
        List<RoomListVO> list = null;

        Map map = queryMap(roomDTO);
        PageHelper.startPage(roomDTO.getPageIndex(),roomDTO.getPageSize());
        List<RoomActivityVO> roomActivityVOS = roomEOMapper.selectRoomInfo(map);
        if (roomActivityVOS != null && roomActivityVOS.size() > 0){
            if (roomDTO.getRequestType() == 0){ //房间正在进行的活动列表
                list = BeanMapper.mapList(roomActivityVOS,RoomListVO.class);    //对象copy
                for (int i = 0; i < list.size(); i++) {
                    String userImg = null;
                    try {
                        userImg = getUserInfo(roomActivityVOS.get(i).getUserId()).getUserImg();
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOGGER.error(ExceptionUtil.getPrintStackTrace(e));  //记录错误日志，但程序任然能执行，程序健壮性
                    }
                    list.get(i).setUserImg(userImg);
                    ActivityVO activity = roomActivityVOS.get(i).getActivityList().get(0);
                    list.get(i).setActivityId(activity.getId());
                    list.get(i).setRoomId(activity.getRoomId());
                    list.get(i).setActivityType(activity.getActivityType());
                    list.get(i).setLotteryTime(activity.getLotteryTime());
                    list.get(i).setLotteryType(activity.getLotteryType());
                    list.get(i).setPeopleNum(activity.getPeopleNum());
                    list.get(i).setTotalGold(activity.getTotalGold());
                    list.get(i).setMaxJoinPeople(activity.getMaxJoinPeople());
                    list.get(i).setTotalRmb(10d);    //这个地方需要读取配置
                }
            }else {
                list = new ArrayList<>();
                for (RoomActivityVO roomActivityVO : roomActivityVOS){
                    List<RoomListVO> roomListVOS = BeanMapper.mapList(roomActivityVO.getActivityList(), RoomListVO.class);
                    if (roomListVOS != null && roomListVOS.size() > 0){     //TODO 这个地方复制属性可以写个工具类，写个注解匹配名称
                        for (int i = 0; i < roomListVOS.size(); i++){
                            roomListVOS.get(i).setActivityId(roomActivityVO.getActivityList().get(i).getId());  //设置活动id
                        }
                    }
                    if (roomListVOS != null && roomActivityVOS.size() > 0){
                        for (RoomListVO roomListVO : roomListVOS){
                            String userImg = null;
                            try {
                                userImg = getUserInfo(roomActivityVO.getUserId()).getUserImg();
                            } catch (Exception e) {
                                e.printStackTrace();
                                LOGGER.error(ExceptionUtil.getPrintStackTrace(e));  //记录错误日志，但程序任然能执行，程序健壮性
                            }
                            roomListVO.setUserImg(userImg);
                            roomListVO.setRoomId(roomActivityVO.getId());
                            roomListVO.setRoomName(roomActivityVO.getRoomName());
                            roomListVO.setRoomNo(roomActivityVO.getRoomNo());
                            roomListVO.setTotalRmb(10d);    //这个地方需要读取配置
                            list.add(roomListVO);
                        }
                    }

                }
            }

        }
        PageInfo<RoomListVO> pageInfo = new PageInfo<RoomListVO>(list);
        return pageInfo;
    }


    @Override
    public RoomEO getRoomEO(Long roomId) {
        if (roomId == null) {
            throw new RoomException(RoomException.ROOM_ID_EMPTY);
        }
        RoomEO roomEO = this.selectById(roomId);
        if (roomEO == null) {
            throw new RoomException(RoomException.ROOM_NOT_EXIT);
        }
        return roomEO;
    }

    @Override
    public int getUserCreateCount(Long userId) {
        if (userId == null){
            throw new RoomException(RoomException.USER_ID_EMPTY);
        }
        RoomEO roomEO = new RoomEO();
        roomEO.setUserId(userId);
        List<RoomEO> select = this.select(roomEO);
        if (select != null){
            return select.size();
        }
        return 0;
    }

    /**
     * 列表查询条件map封装
     * @param roomDTO
     * @return
     */
    protected Map queryMap(RoomDTO roomDTO){
        if (roomDTO != null){
            Map map = new HashMap();
            if (roomDTO.getRequestType() == 1){ //我参与的活动
                UserActivityEO userActivityEO = new UserActivityEO();
                userActivityEO.setUserId(roomDTO.getUserId());
                List<UserActivityEO> activityEOS = userActivityService.select(userActivityEO);
                if (activityEOS != null && activityEOS.size() > 0){
                    List<Long> activityIds = new ArrayList<>();
                    for (UserActivityEO act : activityEOS){
                        activityIds.add(act.getActivityId());
                    }
                    map.put("activityIds",activityIds);
                }
            }
            if (roomDTO.getRequestType() == 2){ //我创建的
                map.put("userId",roomDTO.getUserId());
            }
            map.put("roomId",roomDTO.getRoomId());
            map.put("roomNo",roomDTO.getRoomNo());
            map.put("roomName",roomDTO.getRoomName());
            map.put("activityType",roomDTO.getActivityType());
            map.put("requestType",roomDTO.getRequestType());
            StringBuilder sb = new StringBuilder();
            if (roomDTO.getOrderType() == null || roomDTO.getOrderType() == 1){ //热门排序优先
                if (roomDTO.getHot() == null || roomDTO.getHot() == 0){
                    sb.append(",").append("A.people_num ").append("desc ");
                }else {
                    sb.append(",").append("A.people_num ").append("asc ");
                }
                if (roomDTO.getPrice() == null || roomDTO.getPrice() == 0){
                    sb.append(",").append("A.total_gold ").append("desc ");
                }else {
                    sb.append(",").append("A.total_gold ").append("asc ");
                }
            }else { //价值排序优先
                if (roomDTO.getPrice() == null || roomDTO.getPrice() == 0){
                    sb.append(",").append("A.total_gold ").append("desc ");
                }else {
                    sb.append(",").append("A.total_gold ").append("asc ");
                }
                if (roomDTO.getHot() == null || roomDTO.getHot() == 0){
                    sb.append(",").append("A.people_num ").append("desc ");
                }else {
                    sb.append(",").append("A.people_num ").append("asc ");
                }

            }

            sb.append(",").append("A.lottery_time ").append("desc ");
            map.put("orderDescri",sb.toString());
            int pageIndex = 1, pageSize = 12;
            if (roomDTO.getPageIndex() != null){
                pageIndex = roomDTO.getPageIndex();
            }
            if (roomDTO.getPageSize() != null){
                pageSize = roomDTO.getPageSize();
            }
            if (pageIndex <= 0){
                pageIndex = 1;
            }
            if (pageSize <= 0){
                pageSize = 12;
            }
            roomDTO.setPageIndex(pageIndex);
            roomDTO.setPageSize(pageSize);
            return map;
        }
        return null;
    }

    protected UserAccountVO getUserInfo(Long userId) {
        if (userId == null){
            throw new RoomException(RoomException.USER_ID_EMPTY);
        }
        BaseResponse<UserAccountVO> response = userFeign.getUserInfo(userId);
        UserAccountVO userAccountVO = response.getResult();
        if (userAccountVO == null){
            throw new RoomException(RoomException.USER_NOT_EXIT);
        }
        return userAccountVO;
    }

    /**
     * 创建房间条件
     * 1.一个用户最多10个房间
     * 2.创建房间金币必须小于用户总金币
     *
     * @return
     */
    public boolean createRoomCondition(RoomDTO roomDTO) {
        int roomCount = getUserCreateCount(roomDTO.getUserId());
        if (roomCount >= 10){
            throw new RoomException(RoomException.USER_CREATE_ROOM_MAX);
        }
        return activityService.createActivityCondition(roomDTO);
    }


    /**
     * 创建房间活动返回房间id和活动id
     * @param room
     * @param activityEO
     */
    private Long[] createRoomActivity(RoomEO room, RoomActivityEO activityEO) {
        Long [] result = new Long[2];
        //房间表插入
        room.setRoomNo(RoomHelp.getRoomNo());
        room.setCreateTime(new Date());
        room.setRoomStatus(RoomStatusEnum.NORMAL.code());    //默认房间可用
        room.setIsDeleted(IsDeletedEnum.NO.code());
        room.setLastUpdateTime(new Date());

        Long roomId = insert(room);
        //活动表插入
        activityEO.setRoomId(roomId);
        activityEO.setActivityNo(RoomHelp.getActivityNo());
        activityEO.setEndTime(activityEO.getLotteryTime()); //默认结束时间和开奖时间一致
        activityEO.setActiveStatus(ActivityStatusEnum.ACTIVITY_CREATE.code());    //默认活动已创建，当有一个人进来了活动就开始
        activityEO.setVerifyStatus(ActivityVerifyEnum.VERIFY_WAIT.code());    //审核状态，默认待审核
        activityEO.setCreateTime(new Date());
        activityEO.setStartTime(new Date());    //默认开始时间即为创建时间
        activityEO.setRemainGold(activityEO.getTotalGold());    //活动剩余金额即为活动金额
        activityEO.setLotteryStatus(LotteryStatusEnum.LOTTERY_NO_START.code());
        Long activityId = activityService.insert(activityEO);
        result[0] = roomId;
        result[1] = activityId;
        return result;
    }
}
