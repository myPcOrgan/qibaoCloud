package com.qibao.finance.service;

import com.qibao.common.dto.BaseResponse;
import com.qibao.finance.vo.GoldBalanceVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

/**
 * Created by ljn on 2018/2/2.
 */
@RequestMapping("gold")
public interface IGoldBalanceController {

    @RequestMapping("queryGoldList")
    BaseResponse<GoldBalanceVO> selectGoldBalance(@RequestParam("startTime")String startTime,
                                                  @RequestParam("endTime")String endTime,
                                                  @RequestParam("page")Integer page,
                                                  @RequestParam("size")Integer size)throws ParseException;
}
