package com.qibao.frontend.api.activity.controller;

import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.vo.BoxListVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.feign.IBoxFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/box")
public class BoxControllerImpl {

    @Autowired
    IBoxFeign boxFeign;

    /**
     * 查询宝箱
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectBoxList", method = RequestMethod.POST)
    public BaseResponse<BoxListVO> selectBoxList(@RequestBody BoxDTO dto) {
        return boxFeign.selectBoxList(dto);
    }
}
