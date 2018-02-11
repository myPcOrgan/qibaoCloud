package com.qibao.activity.entity.service;

import com.qibao.activity.entity.dto.ImageDTO;
import com.qibao.activity.entity.vo.ImageVO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("image")
public interface IImageControl {

    /**
     * 查询所有图片
     * @param
     * @return
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    BaseResponse<ImageVO> selectAll();

    /**
     * 增加图片
     * @param dto
     * @return
     */
    @RequestMapping(value = "insertImage", method = RequestMethod.POST)
    BaseResponse insertImage(@RequestBody ImageDTO dto);

    /**
     * 删除图片
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteImage", method = RequestMethod.GET)
    BaseResponse deleteImage(@RequestParam("id") Long id);

    /**
     * 修改图片
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateImage", method = RequestMethod.POST)
    BaseResponse updateImage(@RequestBody ImageDTO dto);

    /**
     * 查询图片
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectImageList", method = RequestMethod.POST)
    BaseResponse selectImageList(@RequestBody ImageDTO dto);

    /**
     * 分页查询图片
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectImageListPage", method = RequestMethod.POST)
    BaseResponse selectImageListPage(@RequestBody ImageDTO dto);
}
