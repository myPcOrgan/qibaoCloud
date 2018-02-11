package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.vo.UserPrizeTotalVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IUserPrizeFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userPrize")
public class UserPrizeControllerImpl {

    @Autowired
    IUserPrizeFeign userPrizeFeign;

    /**
     查询夺宝最近中奖记录
     */
    @ApiOperation(value = "查询夺宝最近中奖记录", notes = "查询夺宝最近中奖记录")
    @RequestMapping(value = "selectRecentlyBoxPrizeList", method = RequestMethod.GET)
    public BaseResponse selectRecentlyBoxPrizeList(@RequestParam("count") int count){
        return userPrizeFeign.selectRecentlyBoxPrizeList(count);
    }

    /**
     查询房间最近中奖记录
     */
    @ApiOperation(value = "查询房间最近中奖记录", notes = "查询房间最近中奖记录")
    @RequestMapping(value = "selectRecentlyRoomPrizeList", method = RequestMethod.GET)
    public BaseResponse selectRecentlyRoomPrizeList(@RequestParam("count") int count){
        return userPrizeFeign.selectRecentlyRoomPrizeList(count);
    }

    /**
     查询上榜总消耗房主
     */
    @ApiOperation(value = "查询上榜总消耗房主", notes = "查询上榜总消耗房主")
    @RequestMapping(value = "selectAllConsumeRoomPrizeList", method = RequestMethod.GET)
    public BaseResponse selectAllConsumeRoomPrizeList(@RequestParam("count") int count){
        return userPrizeFeign.selectAllConsumeRoomPrizeList(count);
    }

    /**
     查询周榜消耗房主
     */
    @ApiOperation(value = "查询周榜消耗房主", notes = "查询周榜消耗房主")
    @RequestMapping(value = "selectWeekConsumeRoomPrizeList", method = RequestMethod.GET)
    public BaseResponse selectWeekConsumeRoomPrizeList(@RequestParam("count") int count){
        return userPrizeFeign.selectWeekConsumeRoomPrizeList(count);
    }

    /**
     获取夺宝人气指数
     */
    @ApiOperation(value = "获取夺宝人气指数", notes = "获取夺宝人气指数")
    @RequestMapping(value = "selectBoxPrizeCount", method = RequestMethod.GET)
    public BaseResponse selectBoxPrizeCount(){
        return userPrizeFeign.selectBoxPrizeCount();
    }

    /**
     * 查询我的roll房信息
     */
    @ApiOperation(value = "查询我的roll房信息", notes = "查询我的roll房信息")
    @RequestMapping(value = "selectMyRoomTotalInfo", method = RequestMethod.GET)
    BaseResponse<UserPrizeTotalVO> selectMyRoomTotalInfo(){
        Long userId = UserContext.getCurrentUserId();
        BaseResponse<UserPrizeTotalVO> response = userPrizeFeign.selectMyRoomTotalInfo(userId);
        return response;
    }
}
