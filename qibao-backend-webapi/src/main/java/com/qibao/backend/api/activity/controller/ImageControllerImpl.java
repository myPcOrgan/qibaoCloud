package com.qibao.backend.api.activity.controller;


import com.qibao.activity.entity.dto.ImageDTO;
import com.qibao.backend.feign.IImageFeign;
import com.qibao.common.dto.BaseResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.qibao.backend.utils.HttpConnectionManager.httpPost;

@RestController
@RequestMapping("api/image")
public class ImageControllerImpl {

    @Autowired
    IImageFeign imageFeign;

    /**
     * 图片上传接口
     *
     * @param
     * @return
     */

    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    public BaseResponse uploadImage(@RequestParam("imageFile") MultipartFile imageFile
            , @RequestParam("imgName") String imgName
            , @RequestParam("imgRemark") String imgRemark
            , @RequestParam("imgType") Integer imgType) {
        BaseResponse response = new BaseResponse();
        String res = "";
        try {
            res = httpPost("https://m.5173.com/api/mobile-fullauto-service/rs/upload/uploadimage", imageFile, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            response.setErrorMessage("新增图片失败");
        }
        if (StringUtils.isNotBlank(res)) {
            JSONObject resObj = JSONObject.fromObject(res);
            if (resObj.getBoolean("success")) {
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImgPath(resObj.getString("path"));
                imageDTO.setImgName(imgName);
                imageDTO.setImgRemark(imgRemark);
                imageDTO.setImgType(imgType);
                if (imageFeign.insertImage(imageDTO).getCode().equals("00")) {
                    response.setErrorMessage("新增图片失败");
                }
            }
        } else {
            response.setErrorMessage("上传图片失败");
        }
        return response;
    }

    /**
     * 分页查询图片
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectImageListPage", method = RequestMethod.POST)
    public BaseResponse selectImageListPage(@RequestBody ImageDTO dto) {
        BaseResponse response = new BaseResponse();
        response.setData(imageFeign.selectImageListPage(dto).getData());
        return response;
    }

    /**
     * 修改图片
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateImage", method = RequestMethod.POST)
    public BaseResponse updateImage(@RequestBody ImageDTO dto) {
        BaseResponse response = new BaseResponse();
        imageFeign.updateImage(dto);
        return response;
    }
}
