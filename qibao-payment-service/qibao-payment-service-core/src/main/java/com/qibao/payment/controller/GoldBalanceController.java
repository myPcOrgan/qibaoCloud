package com.qibao.payment.controller;

import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.DateUtil;
import com.qibao.finance.service.IGoldBalanceController;
import com.qibao.finance.vo.GoldBalanceVO;
import com.qibao.payment.service.IGoldBalanceService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ljn on 2018/2/2.
 * 后台查询金币平衡表
 */
@RestController
@RequestMapping("gold")
public class GoldBalanceController extends BaseController implements IGoldBalanceController{

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private IGoldBalanceService goldBalanceService;

    @Override
    @RequestMapping(value = "queryGoldList",method = RequestMethod.GET)
    @ApiOperation(value="查询金币平衡列表", notes="查询金币平衡列表")
    public BaseResponse<GoldBalanceVO> selectGoldBalance(@RequestParam(value = "startTime",required = false)String startTime,
                                          @RequestParam(value = "endTime",required = false)String endTime,
                                          @RequestParam(value = "page",required = false)Integer page,
                                          @RequestParam(value = "size",required = false)Integer size) throws ParseException {
        BaseResponse<GoldBalanceVO> response = new BaseResponse<>();
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 20;
        }
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(startTime)) {
            map.put("createTime", sdf.parse(startTime));
        }
        if (StringUtils.isNotBlank(endTime)){
            map.put("endTime", DateUtil.oneDateLastTime(sdf.parse(endTime)));
        }
        List<GoldBalanceVO> list = goldBalanceService.selectGoldBalance(map, page, size, "id", true);
        int count = goldBalanceService.countByMap(map);
        response.setData(list);
        response.setTotalCount(count);
        response.setPageIndex(page);
        response.setPageSize(size);
        return response;
    }
}
