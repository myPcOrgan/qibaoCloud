package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.UserPrizeEO;
import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.service.IUserPrizeControl;
import com.qibao.activity.entity.vo.UserPrizeLotteryGoldVO;
import com.qibao.activity.entity.vo.UserPrizeRecentlyVO;
import com.qibao.activity.entity.vo.UserPrizeTotalVO;
import com.qibao.activity.entity.vo.UserPrizeVO;
import com.qibao.activity.service.IUserPrizeService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userPrize")
public class UserPrizeControlImpl extends BaseController implements IUserPrizeControl {

    @Autowired
    private IUserPrizeService userPrizeService;

    @RequestMapping
    public List<UserPrizeEO> index() {
        return userPrizeService.selectAll();
    }

    @RequestMapping(value = "insertUserPrize", method = RequestMethod.POST)
    public BaseResponse<Boolean> insertUserPrize(@RequestBody UserPrizeDTO dto) {
        BaseResponse<Boolean> response = new BaseResponse<Boolean>();
        Boolean result = userPrizeService.insertUserPrize(dto);
        response.setResult(result);
        return response;
    }

    @RequestMapping(value = "selectUserPrizeList", method = RequestMethod.POST)
    public BaseResponse selectPrizeList(@RequestBody UserPrizeDTO dto) {
        BaseResponse response = new BaseResponse();
        UserPrizeEO userPrizeEO = new UserPrizeEO();
        List<UserPrizeEO> prizeList = userPrizeService.select(userPrizeEO);
        response.setData(prizeList);
        return response;
    }

    /**
     * 查询夺宝最近中奖记录
     */
    @RequestMapping(value = "selectRecentlyBoxPrizeList", method = RequestMethod.GET)
    public BaseResponse selectRecentlyBoxPrizeList(@RequestParam("count") int count) {
        BaseResponse response = new BaseResponse();
        List<UserPrizeVO> userPrizeVOList = userPrizeService.selectRecentlyBoxPrizeList("create_time desc", count);
        response.setData(BeanMapper.mapList(userPrizeVOList, UserPrizeRecentlyVO.class));
        return response;
    }

    /**
     * 查询房间最近中奖记录
     */
    @RequestMapping(value = "selectRecentlyRoomPrizeList", method = RequestMethod.GET)
    public BaseResponse selectRecentlyRoomPrizeList(@RequestParam("count") int count) {
        BaseResponse response = new BaseResponse();
        List<UserPrizeVO> userPrizeVOList = userPrizeService.selectRecentlyRoomPrizeList("create_time desc", count);
        response.setData(userPrizeVOList);
        return response;
    }

    /**
     * 查询上榜总消耗房主
     */
    @RequestMapping(value = "selectAllConsumeRoomPrizeList", method = RequestMethod.GET)
    public BaseResponse selectAllConsumeRoomPrizeList(@RequestParam("count") int count) {
        BaseResponse response = new BaseResponse();
        List<UserPrizeVO> userPrizeVOList = userPrizeService.selectAllConsumeRoomPrizeList("win_gold desc", count);
        response.setData(userPrizeVOList);
        return response;
    }

    /**
     * 查询周榜消耗房主
     */
    @RequestMapping(value = "selectWeekConsumeRoomPrizeList", method = RequestMethod.GET)
    public BaseResponse selectWeekConsumeRoomPrizeList(@RequestParam("count") int count) {
        BaseResponse response = new BaseResponse();
        List<UserPrizeVO> userPrizeVOList = userPrizeService.selectWeekConsumeRoomPrizeList("win_gold desc", count);
        response.setData(userPrizeVOList);
        return response;
    }

    /**
     * 查询夺宝人气指数
     */
    @RequestMapping(value = "selectBoxPrizeCount", method = RequestMethod.GET)
    public BaseResponse selectBoxPrizeCount() {
        BaseResponse response = new BaseResponse();
        UserPrizeEO userPrizeEO = new UserPrizeEO();
        userPrizeEO.setPrizeType(2);
        int count = userPrizeService.selectCount(userPrizeEO);
        response.setResult(count);
        return response;
    }

    /**
     查询夺金抽奖金币平衡数据
     */
    @RequestMapping(value = "selectLotteryGoldToDay", method = RequestMethod.POST)
    public BaseResponse<UserPrizeLotteryGoldVO> selectLotteryGoldToDay(@RequestBody UserPrizeDTO dto) {
        BaseResponse<UserPrizeLotteryGoldVO> response = new BaseResponse();
        if (StringUtils.isNotBlank(dto.getBeginDateStr()) && StringUtils.isNotBlank(dto.getEndDateStr())) {
            response.setResult(userPrizeService.selectLotteryGoldToDay(dto.getBeginDateStr(), dto.getEndDateStr()));
        } else {
            response.setErrorMessage("开始结束时间必填");
        }
        return response;
    }

    /**
     * 查询我的roll房信息
     */
    @RequestMapping(value = "selectMyRoomTotalInfo", method = RequestMethod.GET)
    public BaseResponse<UserPrizeTotalVO> selectMyRoomTotalInfo(@RequestParam("userId") Long userId){
        BaseResponse<UserPrizeTotalVO> response = new BaseResponse<>();
        UserPrizeTotalVO result = userPrizeService.selectMyRoomTotalInfo(userId);
        response.setResult(result);
        return response;
    }
}
