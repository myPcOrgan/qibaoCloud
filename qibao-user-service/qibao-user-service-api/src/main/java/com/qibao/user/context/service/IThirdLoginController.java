package com.qibao.user.context.service;

import com.qibao.common.dto.BaseResponse;
import com.qibao.user.context.dto.SendMessageDTO;
import com.qibao.user.context.vo.ThirdLoginVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by 340067 on 2018/1/11.
 */
@RequestMapping("thirdLogin")
public interface IThirdLoginController {

    /**
     * 发送短信接口
     * @param sendMessageDTO 信息内容
     * @return
     */
    @RequestMapping(value = "sendPhoneVerifyCode", method = RequestMethod.POST)
    BaseResponse<String> sendPhoneVerifyCode(@RequestBody SendMessageDTO sendMessageDTO);

    /**
     * 判断验证码是否正确接口
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "checkPhoneVerifyCode", method = RequestMethod.GET)
    BaseResponse<ThirdLoginVO> checkPhoneVerifyCode(@RequestParam("phone") String phone,
                                                    @RequestParam("code") String code,
                                                    @RequestParam("userIp") String userIp);

    /**
     * 保存用户IP接口
     * @param userIp
     * @return
     */
    @RequestMapping(value = "saveIpSession", method = RequestMethod.GET)
    BaseResponse<String> saveIpSession(@RequestParam("userIp") String userIp);

    /**
     * 退出登录接口
     * @param userId
     * @return
     */
    @RequestMapping(value = "delUserCookie", method = RequestMethod.GET)
    BaseResponse<String> delUserCookie(@RequestParam("userId") Long userId);
}
