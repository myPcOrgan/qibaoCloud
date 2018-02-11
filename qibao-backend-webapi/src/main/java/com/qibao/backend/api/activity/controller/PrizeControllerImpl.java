package com.qibao.backend.api.activity.controller;

import com.qibao.activity.entity.dto.PrizeDTO;
import com.qibao.activity.entity.vo.PrizeListVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.backend.feign.IPrizeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/prize")
public class PrizeControllerImpl {

    @Autowired
    IPrizeFeign prizeFeign;

    /**
     * 查询奖品
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectPrizeList", method = RequestMethod.POST)
    public BaseResponse<PrizeListVO> selectPrizeList(@RequestBody PrizeDTO dto) {
        return prizeFeign.selectPrizeList(dto);
    }

    /**
     * 增加奖品
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "insertPrize", method = RequestMethod.POST)
    BaseResponse insertPrize(@RequestBody PrizeDTO dto) {
        BaseResponse response = new BaseResponse();
        prizeFeign.insertPrize(dto);
        return response;
    }

    /**
     * 删除奖品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deletePrize", method = RequestMethod.GET)
    BaseResponse deletePrize(@RequestParam("id") Long id) {
        BaseResponse response = new BaseResponse();
        prizeFeign.deletePrize(id);
        return response;
    }

    /**
     * 修改奖品
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "updatePrize", method = RequestMethod.POST)
    BaseResponse updatePrize(@RequestBody PrizeDTO dto) {
        BaseResponse response = new BaseResponse();
        prizeFeign.updatePrize(dto);
        return response;
    }
}
