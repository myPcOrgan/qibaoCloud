package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.vo.PrizeLotteryVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("lottery")
public interface ILotteryControl {

    /**
     * 夺金抽奖
     * @param dto
     * @return
     */
    @RequestMapping(value = "getLottery", method = RequestMethod.POST)
    BaseResponse<PrizeLotteryVO> getLottery(@RequestBody BoxDTO dto);
}
