package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.BoxEO;
import com.qibao.activity.entity.PrizeEO;
import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.service.ILotteryControl;
import com.qibao.activity.entity.vo.PrizeLotteryVO;
import com.qibao.activity.enums.UserPrizeTypeEnum;
import com.qibao.activity.service.IBoxService;
import com.qibao.activity.service.ILotteryService;
import com.qibao.activity.service.IPrizeService;
import com.qibao.activity.service.IUserPrizeService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/lottery")
public class LotteryControlImpl extends BaseController implements ILotteryControl {

    @Autowired
    private ILotteryService lotteryService;

    @Autowired
    private IBoxService boxService;

    @Autowired
    private IPrizeService prizeService;

    @Autowired
    private IUserPrizeService userPrizeService;

    @RequestMapping(value = "getLottery", method = RequestMethod.POST)
    @Transactional
    public BaseResponse<PrizeLotteryVO> getLottery(@RequestBody BoxDTO dto) {
        BaseResponse<PrizeLotteryVO> response = new BaseResponse();
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new BaseException(1, "参数宝箱id错误");
        }
        if (dto.getBoxExceedCount() == null || dto.getBoxExceedCount() < 0) {
            throw new BaseException(1, "参数连续超本金中奖次数错误");
        }
        BoxEO boxEO = boxService.selectByIdForUpdate(dto.getId());
        if (boxEO == null) {
            throw new BaseException(1, "宝箱:" + dto.getId() + ",未找到");
        }

        //（金币池－金币池 * 系数＋金币挡位数）＝ 最大中奖档位
        Double maxGoldNum = boxEO.getBoxGoldPond() - boxEO.getBoxGoldPond() * boxEO.getBoxGoldCoefficient() + boxEO.getBoxNum();

        //如果用户连续超本金中奖次数超过配置次数,则把奖品最大档位设置成宝箱消耗数
        if (dto.getBoxExceedCount() >= boxEO.getBoxExceedCount()) {
            maxGoldNum = boxEO.getBoxNum();
        }
        Example example = new Example(PrizeEO.class);
        example.createCriteria()
                .andEqualTo("boxId", dto.getId())
                .andLessThanOrEqualTo("prizeNum", maxGoldNum)
                .andEqualTo("isDeleted", false)
                .andEqualTo("isEnable", true);
        List<PrizeEO> prizeList = prizeService.selectByExample(example);
        if (prizeList != null && prizeList.size() > 0) {
            PrizeEO prizeEO = lotteryService.getLottery(prizeList);
            if (prizeEO != null) {
                Double pond = boxEO.getBoxGoldPond() + boxEO.getBoxNum() - prizeEO.getPrizeNum();
                Double overflow = 0.0;
                BoxEO boxEOUpdate=new BoxEO();
                if (pond <= boxEO.getBoxGoldPondMax()) {
                    boxEOUpdate.setBoxGoldPond(pond);
                } else {
                    overflow = pond - boxEO.getBoxGoldPondMax();
                    boxEOUpdate.setBoxGoldPond(boxEO.getBoxGoldPondMax());
                    boxEOUpdate.setBoxGoldPondOverflow(boxEO.getBoxGoldPondOverflow() + overflow);
                }
                boxEOUpdate.setId(boxEO.getId());
                boxService.update(boxEOUpdate);

                UserPrizeDTO userPrizeDTO = new UserPrizeDTO();
                userPrizeDTO.setWinGold(prizeEO.getPrizeNum());
                userPrizeDTO.setPrizeType(UserPrizeTypeEnum.USERPRIZETYPE2.getCode());
                userPrizeDTO.setPrizeUnit(prizeEO.getPrizeUnit());
                userPrizeDTO.setRelateId(prizeEO.getId());
                userPrizeDTO.setUserId(dto.getUserId());
                userPrizeDTO.setBoxGoldPond(boxEO.getBoxNum() - prizeEO.getPrizeNum() - overflow);
                userPrizeDTO.setBoxGoldPondOverflow(overflow);
                userPrizeDTO.setBoxNum(boxEO.getBoxNum());
                userPrizeDTO.setRemark(boxEO.getBoxGoldPond() + "," + boxEO.getBoxGoldCoefficient() + "," + boxEO.getBoxGoldPondMax() + "," + boxEO.getBoxGoldPondOverflow());
                if(!userPrizeService.insertUserPrize(userPrizeDTO)){
                    response.setErrorMessage("宝箱:" + dto.getId() + ",增加中奖记录失败");
                    throw new BaseException(1, "宝箱:" + dto.getId() + ",增加中奖记录失败");
                }
                response.setResult(BeanMapper.map(prizeEO, PrizeLotteryVO.class));
            } else {
                throw new BaseException(1, "宝箱:" + dto.getId() + ",抽奖异常");
            }
        } else {
            throw new BaseException(1, "宝箱:" + dto.getId() + ",没有配置奖品");
        }
        return response;
    }
}
