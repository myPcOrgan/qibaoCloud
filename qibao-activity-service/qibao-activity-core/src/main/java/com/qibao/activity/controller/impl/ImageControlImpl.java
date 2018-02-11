package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.service.IImageControl;
import com.qibao.activity.entity.ImageEO;
import com.qibao.activity.entity.dto.ImageDTO;
import com.qibao.activity.entity.vo.ImageVO;
import com.qibao.activity.service.IImageService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageControlImpl extends BaseController implements IImageControl {

    @Autowired
    private IImageService imageService;

    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public BaseResponse<ImageVO> selectAll() {
        BaseResponse<ImageVO> response = new BaseResponse();
        response.setData(BeanMapper.mapList(imageService.selectAll(), ImageVO.class));
        return response;
    }

    @RequestMapping(value = "insertImage", method = RequestMethod.POST)
    public BaseResponse insertImage(@RequestBody ImageDTO dto) {
        BaseResponse response = new BaseResponse();
        try {
            ImageEO imageEO = new ImageEO();
            imageEO.setImgPath(dto.getImgPath());
            imageEO.setImgName(dto.getImgName());
            imageEO.setImgRemark(dto.getImgRemark());
            imageEO.setImgType(dto.getImgType());
            imageService.insert(imageEO);
        } catch (BaseException e) {
            this.LOGGER.error("新增图片接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("新增图片接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "deleteImage", method = RequestMethod.GET)
    public BaseResponse deleteImage(@RequestParam("id") Long id) {
        BaseResponse response = new BaseResponse();
        imageService.deleteByPrimary(id);
        return response;
    }

    @RequestMapping(value = "updateImage", method = RequestMethod.POST)
    public BaseResponse updateImage(@RequestBody ImageDTO dto) {
        BaseResponse response = new BaseResponse();
        ImageEO imageEO = new ImageEO();
        imageEO.setId(dto.getId());
        imageEO.setImgPath(dto.getImgPath());
        imageEO.setImgName(dto.getImgName());
        imageEO.setImgRemark(dto.getImgRemark());
        imageEO.setImgType(dto.getImgType());
        imageService.update(imageEO);
        return response;
    }

    @RequestMapping(value = "selectImageList", method = RequestMethod.POST)
    public BaseResponse selectImageList(@RequestBody ImageDTO dto) {
        BaseResponse response = new BaseResponse();
        ImageEO imageEO = new ImageEO();
        imageEO.setId(dto.getId());
        imageEO.setImgPath(dto.getImgPath());
        imageEO.setImgName(dto.getImgName());
        imageEO.setImgRemark(dto.getImgRemark());
        imageEO.setImgType(dto.getImgType());
        try {
            response.setData(imageService.select(imageEO));
        } catch (BaseException e) {
            this.LOGGER.error("查询图片列表接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("查询图片列表接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "selectImageListPage", method = RequestMethod.POST)
    public BaseResponse selectImageListPage(@RequestBody ImageDTO dto) {
        BaseResponse response = new BaseResponse();
        try {
            response.setData(imageService.selectImageListPage(dto));
        } catch (BaseException e) {
            this.LOGGER.error("查询图片列表接口发生异常:{}" + e.getErrorMsg());
            response.setErrorMessage(e.getErrorMsg());
        } catch (Exception e) {
            this.LOGGER.error("查询图片列表接口发生未知异常:{}", e.getMessage());
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }
}
