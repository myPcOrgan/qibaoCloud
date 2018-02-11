package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.activity.entity.vo.PrizeLotteryVO;
import com.qibao.activity.enums.UserPrizeTypeEnum;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IBoxFeign;
import com.qibao.frontend.feign.ILotteryFeign;
import com.qibao.frontend.feign.IUserInfoFeign;
import com.qibao.frontend.feign.IUserPrizeFeign;
import com.qibao.user.context.dto.UpdateGoldInfoDTO;
import com.qibao.user.context.enums.GoldTypeEnum;
import com.qibao.user.context.vo.UserAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lottery")
public class LotteryControllerImpl {

    @Autowired
    ILotteryFeign lotteryFeign;

    @Autowired
    IBoxFeign boxFeign;

    @Autowired
    IUserPrizeFeign userPrizeFeign;

    @Autowired
    IUserInfoFeign userInfoFeign;

    /**
     * 夺金抽奖
     * @param dto
     * @return
     */
    @RequestMapping(value = "getLottery", method = RequestMethod.POST)
    public BaseResponse<PrizeLotteryVO> getLottery(@RequestBody BoxDTO dto) {
        BaseResponse<PrizeLotteryVO> response = new BaseResponse();
        if (dto.getId() == null || dto.getId() <= 0) {
            response.setErrorMessage("参数宝箱id错误");
            return response;
        }
        Long userId = UserContext.getCurrentUserId();
        if (userId == null){
            throw new BaseException(01,"用户未登录");
        }
        BaseResponse<UserAccountVO> userAccountVO = userInfoFeign.getUserInfo(userId);
        BoxVO boxVO = boxFeign.selectById(dto).getResult();
        if (boxVO != null) {

            if (userAccountVO.getCode().equals("00")) {
                UpdateGoldInfoDTO updateGoldInfoDTO = new UpdateGoldInfoDTO();
                updateGoldInfoDTO.setUserId(userId);
                updateGoldInfoDTO.setGoldNum(boxVO.getBoxNum());
                updateGoldInfoDTO.setType(GoldTypeEnum.REDUCETOTALGOLD.getCode());
                updateGoldInfoDTO.setDesp("宝箱" + boxVO.getBoxName() + "消耗" + boxVO.getBoxNum());
                BaseResponse user = userInfoFeign.updateUserGoldInfos(updateGoldInfoDTO);
                UserPrizeDTO userPrizeDTO = new UserPrizeDTO();
                userPrizeDTO.setWinGold(boxVO.getBoxNum());
                userPrizeDTO.setPrizeType(UserPrizeTypeEnum.USERPRIZETYPE3.getCode());
                userPrizeDTO.setPrizeUnit(boxVO.getBoxUnit());
                userPrizeDTO.setRelateId(boxVO.getId());
                userPrizeDTO.setUserId(userId);
                userPrizeFeign.insertUserPrize(userPrizeDTO).getResult();
                if (user.getCode().equals("00")) {
                    dto.setBoxExceedCount(userAccountVO.getResult().getExceedCount());
                    dto.setUserId(userId);
                    try {
                        response = lotteryFeign.getLottery(dto);
                        //如果奖品高于本金,则用户超本金次数+1,否则清零
                        updateGoldInfoDTO = new UpdateGoldInfoDTO();
                        updateGoldInfoDTO.setUserId(userId);
                        updateGoldInfoDTO.setGoldNum(response.getResult().getPrizeNum());
                        updateGoldInfoDTO.setDesp("奖品" + response.getResult().getPrizeName() + "获得" + response.getResult().getPrizeNum());
                        if (response.getResult().getPrizeNum() > boxVO.getBoxNum()) {
                            updateGoldInfoDTO.setType(GoldTypeEnum.ALLWINNUM.getCode());
                            user = userInfoFeign.updateUserGoldInfos(updateGoldInfoDTO);
                        } else {
                            updateGoldInfoDTO.setType(GoldTypeEnum.ALLWINNUMNULL.getCode());
                            user = userInfoFeign.updateUserGoldInfos(updateGoldInfoDTO);
                        }
                        if (!user.getCode().equals("00")) {
                            //补开箱消耗逻辑
                            response.setErrorMessage(user.getMessage());
                        }
                    } catch (Exception e) {
                        //如果抽奖服务失败,则补一次开箱消耗逻辑
                        updateGoldInfoDTO = new UpdateGoldInfoDTO();
                        updateGoldInfoDTO.setUserId(userId);
                        updateGoldInfoDTO.setGoldNum(boxVO.getBoxNum());
                        updateGoldInfoDTO.setType(GoldTypeEnum.ADDTOTALGOLD.getCode());
                        updateGoldInfoDTO.setDesp("获得");
                        userInfoFeign.updateUserGoldInfos(updateGoldInfoDTO);
                        response.setErrorMessage(e.getMessage());
                        return response;
                    }
                } else {
                    response.setErrorMessage(user.getMessage());
                }
            } else {
                response.setErrorMessage(userAccountVO.getMessage());
            }
        } else {
            response.setErrorMessage("未找到宝箱");
            return response;
        }
        return response;
    }
}
