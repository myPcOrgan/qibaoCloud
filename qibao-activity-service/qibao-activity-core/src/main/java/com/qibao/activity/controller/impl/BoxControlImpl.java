package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.BoxEO;
import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.service.IBoxControl;
import com.qibao.activity.entity.vo.BoxListVO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.activity.service.IBoxService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxControlImpl extends BaseController implements IBoxControl {

    @Autowired
    private IBoxService boxService;

    @RequestMapping
    public List<BoxEO> index() {
        return boxService.selectAll();
    }

    @RequestMapping(value = "insertBox", method = RequestMethod.POST)
    public BaseResponse insertBox(@RequestBody BoxDTO dto) {
        BaseResponse response = new BaseResponse();
        try {
            BoxEO boxEO = new BoxEO();
            boxEO.setBoxNo(dto.getBoxNo());
            boxEO.setBoxName(dto.getBoxName());
            boxEO.setGameNo(dto.getGameNo());
            boxEO.setBoxNum(dto.getBoxNum());
            boxEO.setBoxType(dto.getBoxType());
            boxEO.setBoxUnit(dto.getBoxUnit());
            boxEO.setImgId(dto.getImgId());
            boxEO.setBoxGoldCoefficient(dto.getBoxGoldCoefficient());
            boxEO.setBoxGoldPondMax(dto.getBoxGoldPondMax());
            boxEO.setBoxExceedCount(dto.getBoxExceedCount());
            boxEO.setCreateTime(new Date());
            boxEO.setIsDeleted(false);
            boxEO.setIsEnable(true);
            boxService.insertBox(boxEO);
        } catch (BaseException e) {
            this.LOGGER.error("新增宝箱接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("新增宝箱接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "deleteBox", method = RequestMethod.GET)
    public BaseResponse deleteBox(@RequestParam("id") Long id) {
        BaseResponse response = new BaseResponse();
        boxService.selectByIdForUpdate(id);
        BoxEO boxEO = new BoxEO();
        boxEO.setId(id);
        boxEO.setLastUpdateTime(new Date());
        boxEO.setIsDeleted(true);
        boxService.update(boxEO);
        return response;
    }

    @RequestMapping(value = "updateBox", method = RequestMethod.POST)
    public BaseResponse updateBox(@RequestBody BoxDTO dto) {
        BaseResponse response = new BaseResponse();
        boxService.selectByIdForUpdate(dto.getId());
        BoxEO boxEO = new BoxEO();
        boxEO.setId(dto.getId());
        boxEO.setBoxNo(dto.getBoxNo());
        boxEO.setBoxName(dto.getBoxName());
        boxEO.setGameNo(dto.getGameNo());
        boxEO.setBoxNum(dto.getBoxNum());
        boxEO.setBoxType(dto.getBoxType());
        boxEO.setBoxUnit(dto.getBoxUnit());
        boxEO.setImgId(dto.getImgId());
        boxEO.setBoxGoldCoefficient(dto.getBoxGoldCoefficient());
        boxEO.setBoxGoldPondMax(dto.getBoxGoldPondMax());
        boxEO.setBoxExceedCount(dto.getBoxExceedCount());
        boxEO.setLastUpdateTime(new Date());
        boxEO.setIsEnable(dto.getEnable());
        boxService.update(boxEO);
        return response;
    }

    @RequestMapping(value = "selectBoxList", method = RequestMethod.POST)
    public BaseResponse<BoxListVO> selectBoxList(@RequestBody BoxDTO dto) {
        BaseResponse<BoxListVO> response = new BaseResponse();
        try {
            if (StringUtils.isBlank(dto.getGameNo())) {
                throw new BaseException(1, "游戏编号不能为空");
            }
            BoxEO boxEO = new BoxEO();
            boxEO.setGameNo(dto.getGameNo());
            List<BoxEO> boxList = boxService.selectBoxList(boxEO);
            response.setData(BeanMapper.mapList(boxList, BoxListVO.class));
        } catch (BaseException e) {
            this.LOGGER.error("查询宝箱列表接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("查询宝箱列表接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "selectById", method = RequestMethod.POST)
    public BaseResponse<BoxVO> selectById(@RequestBody BoxDTO dto) {
        BaseResponse<BoxVO> response = new BaseResponse();
        if (dto.getId() == null || dto.getId() <= 0) {
            throw new BaseException(1, "参数宝箱id错误");
        }
        BoxEO boxEO = boxService.selectById(dto.getId());
        response.setResult(BeanMapper.map(boxEO, BoxVO.class));
        return response;
    }

    @RequestMapping(value = "selectBoxDetailList", method = RequestMethod.POST)
    public BaseResponse<BoxVO> selectBoxDetailList(@RequestBody BoxDTO dto) {
        BaseResponse<BoxVO> response = new BaseResponse();
        try {
            List<BoxVO> boxList = boxService.selectBoxDetailList(dto.getBoxName());
            response.setData(boxList);
        } catch (BaseException e) {
            this.LOGGER.error("查询宝箱列表接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("查询宝箱列表接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }
}
