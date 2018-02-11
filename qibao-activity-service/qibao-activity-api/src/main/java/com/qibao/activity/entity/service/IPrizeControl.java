package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.PrizeDTO;
import com.qibao.activity.entity.vo.PrizeListVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("prize")
public interface IPrizeControl {

    /**
     * 增加奖品
     * @param dto
     * @return
     */
    @RequestMapping(value = "insertPrize", method = RequestMethod.POST)
    BaseResponse insertPrize(@RequestBody PrizeDTO dto);

    /**
     * 删除奖品
     * @param id
     * @return
     */
    @RequestMapping(value = "deletePrize", method = RequestMethod.GET)
    BaseResponse deletePrize(@RequestParam("id") Long id);

    /**
     * 修改奖品
     * @param dto
     * @return
     */
    @RequestMapping(value = "updatePrize", method = RequestMethod.POST)
    BaseResponse updatePrize(@RequestBody PrizeDTO dto);

    /**
     * 查询奖品
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectPrizeList", method = RequestMethod.POST)
    BaseResponse<PrizeListVO> selectPrizeList(@RequestBody PrizeDTO dto);
}
