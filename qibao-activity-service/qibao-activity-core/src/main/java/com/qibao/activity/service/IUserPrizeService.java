package com.qibao.activity.service;

import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.vo.UserPrizeLotteryGoldVO;
import com.qibao.activity.entity.vo.UserPrizeTotalVO;
import com.qibao.activity.entity.vo.UserPrizeVO;
import com.qibao.common.service.IBaseService;

import java.util.List;

public interface IUserPrizeService extends IBaseService<UserPrizeEO> {

    /**
     * 添加记录
     */
    boolean insertUserPrize(UserPrizeDTO dto);

    /**
     查询夺宝最近中奖记录
     */
    List<UserPrizeVO> selectRecentlyBoxPrizeList(String orderBy, int limit);

    /**
     查询房间最近中奖记录
     */
    List<UserPrizeVO> selectRecentlyRoomPrizeList(String orderBy, int limit);

    /**
     查询上榜总消耗房主
     */
    List<UserPrizeVO> selectAllConsumeRoomPrizeList(String orderBy, int limit);

    /**
     查询周榜消耗房主
     */
    List<UserPrizeVO> selectWeekConsumeRoomPrizeList(String orderBy, int limit);

    UserPrizeLotteryGoldVO selectLotteryGoldToDay(String beginDateStr, String endDateStr);

    /**
     * 查询我的roll房统计信息
     * @param userId
     * @return
     */
    UserPrizeTotalVO selectMyRoomTotalInfo(Long userId);

}
