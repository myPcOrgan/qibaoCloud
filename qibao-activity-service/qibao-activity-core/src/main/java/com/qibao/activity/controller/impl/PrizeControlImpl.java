package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.service.IPrizeControl;
import com.qibao.activity.entity.PrizeEO;
import com.qibao.activity.entity.dto.PrizeDTO;
import com.qibao.activity.entity.vo.PrizeListVO;
import com.qibao.activity.service.IPrizeService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prize")
public class PrizeControlImpl extends BaseController implements IPrizeControl {

    @Autowired
    private IPrizeService prizeService;

    @RequestMapping
    public List<PrizeEO> index() {
        return prizeService.selectAll();
    }

    @RequestMapping(value = "insertPrize", method = RequestMethod.POST)
    public BaseResponse insertPrize(@RequestBody PrizeDTO dto) {
        BaseResponse response = new BaseResponse();
        try {
            PrizeEO prizeEO = new PrizeEO();
            prizeEO.setBoxId(dto.getBoxId());
            prizeEO.setPrizeNo(dto.getPrizeNo());
            prizeEO.setPrizeName(dto.getPrizeName());
            prizeEO.setPrizeNum(dto.getPrizeNum());
            prizeEO.setPrizeType(dto.getPrizeType());
            prizeEO.setPrizeUnit(dto.getPrizeUnit());
            prizeEO.setPrizeProbability(1.0);
            prizeEO.setImgId(dto.getImgId());
            prizeEO.setIsDeleted(false);
            prizeEO.setIsEnable(true);
            prizeService.insert(prizeEO);
        } catch (BaseException e) {
            this.LOGGER.error("增加奖品接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("增加奖品接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "deletePrize", method = RequestMethod.GET)
    public BaseResponse deletePrize(@RequestParam("id") Long id) {
        BaseResponse response = new BaseResponse();
        PrizeEO prizeEO = new PrizeEO();
        prizeEO.setId(id);
        prizeEO.setLastUpdateTime(new Date());
        prizeEO.setIsDeleted(true);
        prizeService.update(prizeEO);
        return response;
    }

    @RequestMapping(value = "updatePrize", method = RequestMethod.POST)
    public BaseResponse updatePrize(@RequestBody PrizeDTO dto) {
        BaseResponse response = new BaseResponse();
        PrizeEO prizeEO = new PrizeEO();
        prizeEO.setId(dto.getId());
        prizeEO.setPrizeNo(dto.getPrizeNo());
        prizeEO.setPrizeName(dto.getPrizeName());
        prizeEO.setPrizeNum(dto.getPrizeNum());
        prizeEO.setPrizeType(dto.getPrizeType());
        prizeEO.setPrizeUnit(dto.getPrizeUnit());
        prizeEO.setPrizeProbability(dto.getPrizeProbability());
        prizeEO.setImgId(dto.getImgId());
        prizeEO.setPrizeName(dto.getPrizeName());
        prizeEO.setLastUpdateTime(new Date());
        prizeEO.setIsEnable(dto.getEnable());
        prizeService.update(prizeEO);
        return response;
    }

    @RequestMapping(value = "selectPrizeList", method = RequestMethod.POST)
    public BaseResponse<PrizeListVO> selectPrizeList(@RequestBody PrizeDTO dto) {
        BaseResponse<PrizeListVO> response = new BaseResponse();
        try {
            if (dto.getBoxId() == null) {
                throw new BaseException(1, "宝箱编号不能为空");
            }
            PrizeEO prizeEO = new PrizeEO();
            prizeEO.setBoxId(dto.getBoxId());
            List<PrizeEO> prizeList = prizeService.selectPrizeList(prizeEO);
            response.setData(BeanMapper.mapList(prizeList, PrizeListVO.class));
        } catch (BaseException e) {
            this.LOGGER.error("查询奖品列表接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("查询奖品列表接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }
}
