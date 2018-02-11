package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.vo.UserPrizeLotteryGoldVO;
import com.qibao.activity.entity.vo.UserPrizeTotalVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("userPrize")
public interface IUserPrizeControl {

    @RequestMapping(value = "insertUserPrize", method = RequestMethod.POST)
    BaseResponse insertUserPrize(@RequestBody UserPrizeDTO dto);

    @RequestMapping(value = "selectUserPrizeList", method = RequestMethod.POST)
    BaseResponse selectPrizeList(@RequestBody UserPrizeDTO dto);

    /**
     * 查询夺宝最近中奖记录
     */
    @RequestMapping(value = "selectRecentlyBoxPrizeList", method = RequestMethod.GET)
    BaseResponse selectRecentlyBoxPrizeList(@RequestParam("count") int count);

    /**
     * 查询房间最近中奖记录
     */
    @RequestMapping(value = "selectRecentlyRoomPrizeList", method = RequestMethod.GET)
    BaseResponse selectRecentlyRoomPrizeList(@RequestParam("count") int count);

    /**
     * 查询上榜总消耗房主
     */
    @RequestMapping(value = "selectAllConsumeRoomPrizeList", method = RequestMethod.GET)
    BaseResponse selectAllConsumeRoomPrizeList(@RequestParam("count") int count);

    /**
     * 查询周榜消耗房主
     */
    @RequestMapping(value = "selectWeekConsumeRoomPrizeList", method = RequestMethod.GET)
    BaseResponse selectWeekConsumeRoomPrizeList(@RequestParam("count") int count);

    /**
     * 获取夺宝人气指数
     */
    @RequestMapping(value = "selectBoxPrizeCount", method = RequestMethod.GET)
    BaseResponse selectBoxPrizeCount();

    /**
     查询夺金抽奖金币平衡数据
     */
    @RequestMapping(value = "selectLotteryGoldToDay", method = RequestMethod.POST)
    BaseResponse<UserPrizeLotteryGoldVO> selectLotteryGoldToDay(@RequestBody UserPrizeDTO dto);

    /**
     * 查询我的roll房信息
     */
    @RequestMapping(value = "selectMyRoomTotalInfo", method = RequestMethod.GET)
    BaseResponse<UserPrizeTotalVO> selectMyRoomTotalInfo(@RequestParam("userId") Long userId);
}
