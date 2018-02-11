package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.dto.PrizeDTO;
import com.qibao.activity.entity.vo.PrizeListVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.feign.IPrizeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/prize")
public class PrizeControllerImpl {

    @Autowired
    IPrizeFeign prizeFeign;

    /**
     * 查询奖品
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectPrizeList", method = RequestMethod.POST)
    public BaseResponse<PrizeListVO> selectPrizeList(@RequestBody PrizeDTO dto) {
        return prizeFeign.selectPrizeList(dto);
    }
}
