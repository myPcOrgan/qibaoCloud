package com.qibao.activity.service.impl;

import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.vo.UserPrizeLotteryGoldVO;
import com.qibao.activity.entity.vo.UserPrizeTotalVO;
import com.qibao.activity.entity.vo.UserPrizeVO;
import com.qibao.activity.mapper.UserPrizeEOMapper;
import com.qibao.activity.service.IRoomService;
import com.qibao.activity.service.IUserActivityService;
import com.qibao.activity.service.IUserPrizeService;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPrizeServiceImpl extends BaseService<UserPrizeEO> implements IUserPrizeService {

    @Autowired
    private UserPrizeEOMapper userPrizeEOMapper;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IUserActivityService userActivityService;

    @Override
    public boolean insertUserPrize(UserPrizeDTO dto) {
        if (dto != null){
            UserPrizeEO userPrizeEO = BeanMapper.map(dto,UserPrizeEO.class);
            userPrizeEO.setCreateTime(new Date());
            this.insert(userPrizeEO);
            return true;
        }
        return false;
    }

    /**
     查询夺宝最近中奖记录
     */
    @Override
    public List<UserPrizeVO> selectRecentlyBoxPrizeList(String orderBy, int limit) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("orderBy", orderBy);
        queryMap.put("limit", limit);
        List<UserPrizeVO> userPrizeVOList = userPrizeEOMapper.selectRecentlyBoxPrizeList(queryMap);
        return userPrizeVOList;
    }

    /**
     查询房间最近中奖记录
     */
    @Override
    public List<UserPrizeVO> selectRecentlyRoomPrizeList(String orderBy, int limit) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("orderBy", orderBy);
        queryMap.put("limit", limit);
        List<UserPrizeVO> userPrizeVOList = userPrizeEOMapper.selectRecentlyRoomPrizeList(queryMap);
        return userPrizeVOList;
    }

    /**
     查询上榜总消耗房主
     */
    @Override
    public List<UserPrizeVO> selectAllConsumeRoomPrizeList(String orderBy, int limit) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("orderBy", orderBy);
        queryMap.put("limit", limit);
        List<UserPrizeVO> userPrizeVOList = userPrizeEOMapper.selectAllConsumeRoomPrizeList(queryMap);
        return userPrizeVOList;
    }

    /**
     查询周榜消耗房主
     */
    @Override
    public List<UserPrizeVO> selectWeekConsumeRoomPrizeList(String orderBy, int limit) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("orderBy", orderBy);
        queryMap.put("limit", limit);
        List<UserPrizeVO> userPrizeVOList = userPrizeEOMapper.selectWeekConsumeRoomPrizeList(queryMap);
        return userPrizeVOList;
    }

    /**
     查询夺金抽奖金币平衡数据
     */
    @Override
    public UserPrizeLotteryGoldVO selectLotteryGoldToDay(String beginDateStr, String endDateStr) {
        if (StringUtils.isNotBlank(beginDateStr) && StringUtils.isNotBlank(endDateStr)) {
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("beginDateStr", beginDateStr);
            queryMap.put("endDateStr", endDateStr);
            return userPrizeEOMapper.selectLotteryGoldToDay(queryMap);
        } else {
            return null;
        }
    }

    @Override
    public UserPrizeTotalVO selectMyRoomTotalInfo(Long userId) {
        UserPrizeTotalVO result = new UserPrizeTotalVO();
        int userCreateCount = roomService.getUserCreateCount(userId);
        int iJoinActivityCount = userActivityService.getIJoinActivityCount(userId);
        UserPrizeEO userPrizeEO = new UserPrizeEO();
        userPrizeEO.setUserId(userId);
        userPrizeEO.setPrizeType(1);
        List<UserPrizeEO> select = this.select(userPrizeEO);
        result.setCreateRoomNum(userCreateCount);
        result.setJoinRoomActivityNum(iJoinActivityCount);
        if (select != null && select.size() > 0){
            result.setPrizeActivityNum(select.size());
            double sum = 0d;
            for (UserPrizeEO userPrize : select){
                sum = sum + (userPrize.getWinGold() == null ? 0 : userPrize.getWinGold());
            }
            result.setTotalGold(sum);
        }else {
            result.setPrizeActivityNum(0);
            result.setTotalGold(0);
        }
        return result;
    }
}
