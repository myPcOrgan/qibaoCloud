package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.vo.BoxListVO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("box")
public interface IBoxControl {

    /**
     * 增加宝箱
     * @param dto
     * @return
     */
    @RequestMapping(value = "insertBox", method = RequestMethod.POST)
    BaseResponse insertBox(@RequestBody BoxDTO dto);

    /**
     * 删除宝箱
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteBox", method = RequestMethod.GET)
    BaseResponse deleteBox(@RequestParam("id") Long id);

    /**
     * 修改宝箱
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateBox", method = RequestMethod.POST)
    BaseResponse updateBox(@RequestBody BoxDTO dto);

    /**
     * 查询宝箱
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectBoxList", method = RequestMethod.POST)
    BaseResponse<BoxListVO> selectBoxList(@RequestBody BoxDTO dto);

    /**
     * 查询宝箱,根据id
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectById", method = RequestMethod.POST)
    BaseResponse<BoxVO> selectById(@RequestBody BoxDTO dto);

    /**
     * 查询宝箱明细
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectBoxDetailList", method = RequestMethod.POST)
    BaseResponse<BoxVO> selectBoxDetailList(@RequestBody BoxDTO dto);
}
