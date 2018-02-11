package com.qibao.frontend.api.activity.controller;


import com.qibao.activity.entity.vo.ImageVO;
import com.qibao.frontend.feign.IImageFeign;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("api/image")
public class ImageControllerImpl {

    @Autowired
    IImageFeign imageFeign;

//    /**
//     * 同步所有图片到js文件接口
//     *
//     * @param
//     * @return
//     */
//    @RequestMapping(value = "setImage", method = RequestMethod.GET)
//    public BaseResponse setImage() {
//        BaseResponse response = new BaseResponse();
//        List<ImageVO> imageList = imageFeign.selectAll().getData();
//        if (imageList != null) {
//            if (ToJsUtils.setJs("image.js", JSONArray.fromObject(imageList).toString())) {
//                response.setMessage("成功");
//            } else {
//                response.setMessage("失败");
//            }
//        }
//        return response;
//    }

    /**
     * 获取图片js文件接口
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getImage", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response) {
        OutputStream out = null;
        try {
            // 设置输出的格式
            response.reset();
            response.setContentType("application/javascript");
            response.addHeader("Content-Disposition", "attachment; filename=\"image.js\"");
            out = response.getOutputStream();
            List<ImageVO> imageList = imageFeign.selectAll().getData();
            String imageJson = "var IMAGE=" + JSONArray.fromObject(imageList).toString();
            out.write(imageJson.getBytes("utf-8"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
