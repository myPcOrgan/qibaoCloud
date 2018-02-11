package com.qibao.activity.mapper;

import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.vo.UserPrizeLotteryGoldVO;
import com.qibao.activity.entity.vo.UserPrizeVO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface UserPrizeEOMapper extends IBaseMapper<UserPrizeEO> {

    /**
    查询夺宝最近中奖记录
    */
    List<UserPrizeVO> selectRecentlyBoxPrizeList(Map<String, Object> queryMap);

    /**
     查询房间最近中奖记录
     */
    List<UserPrizeVO> selectRecentlyRoomPrizeList(Map<String, Object> queryMap);

    /**
     查询上榜总消耗房主
     */
    List<UserPrizeVO> selectAllConsumeRoomPrizeList(Map<String, Object> queryMap);

    /**
     查询周榜消耗房主
     */
    List<UserPrizeVO> selectWeekConsumeRoomPrizeList(Map<String, Object> queryMap);

    /**
     查询夺金抽奖金币平衡数据
     */
    UserPrizeLotteryGoldVO selectLotteryGoldToDay(Map<String, Object> queryMap);
}