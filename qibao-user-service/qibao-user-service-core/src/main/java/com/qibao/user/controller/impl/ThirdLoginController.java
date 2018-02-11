package com.qibao.user.controller.impl;


import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.user.context.dto.SendMessageDTO;
import com.qibao.user.context.vo.ThirdLoginVO;
import com.qibao.user.context.service.IThirdLoginController;
import com.qibao.user.entity.UserAccountEO;
import com.qibao.user.exceptions.UserException;
import com.qibao.user.service.ICookieService;
import com.qibao.user.service.ISendMessage;
import com.qibao.user.service.IThirdLoginService;
import com.qibao.user.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 *
 *
 */
@RestController
@RequestMapping("thirdLogin")
public class ThirdLoginController extends BaseController implements IThirdLoginController {

    @Autowired
    IThirdLoginService thirdLoginService;
    @Autowired
    IUserService userService;
    @Autowired
    ICookieService cookieService;
    @Autowired
    ISendMessage sendMessage;


    @RequestMapping(value = "checkPhoneVerifyCode", method = RequestMethod.GET)
    @ApiOperation(value = "判断验证码是否正确接口", notes = "判断验证码是否正确接口")
    public BaseResponse<ThirdLoginVO> checkPhoneVerifyCode(@RequestParam("phone") String phone,
                                             @RequestParam("code") String code,
                                             @RequestParam("userIp") String userIp){
        BaseResponse<ThirdLoginVO> baseResponse = new BaseResponse();
        if (StringUtils.isBlank(phone)){
            throw new UserException(UserException.PHONE_EMPTY);
        }
        if (!phone.matches("^1(3|4|5|7|8)\\d{9}$")){
            throw new UserException(UserException.PHONE_ERROR_FORMAT);
        }
        if (StringUtils.isBlank(code)){
            throw new BaseException(01,"验证码不能为空");
        }
        if (StringUtils.isBlank(userIp)){
            throw new UserException(UserException.IP_EMPTY);
        }
        UserAccountEO userAccountEO = thirdLoginService.checkPhoneVerifyCode(phone, code, userIp);
        ThirdLoginVO thirdLoginVO = new ThirdLoginVO();
        if (userAccountEO == null){
            thirdLoginVO.setUrl("03");
        } else {
            String authkey = cookieService.createSession(userAccountEO.getId());
            BeanMapper.copyProperties(userAccountEO, thirdLoginVO);
            thirdLoginVO.setAuthkey(authkey);
        }
        baseResponse.setResult(thirdLoginVO);

        return baseResponse;
    }


    @RequestMapping(value = "sendPhoneVerifyCode", method = RequestMethod.POST)
    @ApiOperation(value = "发送短信接口", notes = "发送短信接口")
    public BaseResponse<String> sendPhoneVerifyCode(@RequestBody SendMessageDTO sendMessageDTO){
        BaseResponse<String> baseResponse = new BaseResponse();
        if (sendMessageDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        if (StringUtils.isBlank(sendMessageDTO.getPhone())){
            throw new UserException(UserException.PHONE_EMPTY);
        }
        if (!sendMessageDTO.getPhone().matches("^1(3|4|5|7|8)\\d{9}$")){
            throw new UserException(UserException.PHONE_ERROR_FORMAT);
        }
        if (StringUtils.isBlank(sendMessageDTO.getContext())){
            throw new BaseException(01,"信息内容不能为空");
        }
        if (StringUtils.isBlank(sendMessageDTO.getUserIp())){
            throw new UserException(UserException.IP_EMPTY);
        }
        Boolean aBoolean = sendMessage.sendMessage(sendMessageDTO);
        if (!aBoolean){
            throw new BaseException(01,"消息发送失败");
        }

        return baseResponse;
    }

    @RequestMapping(value = "saveIpSession", method = RequestMethod.GET)
    @ApiOperation(value = "保存用户IP接口", notes = "保存用户IP接口")
    public BaseResponse<String> saveIpSession(@RequestParam("userIp") String userIp){
        BaseResponse<String> baseResponse = new BaseResponse();
        if (StringUtils.isBlank(userIp)){
            throw new UserException(UserException.IP_EMPTY);
        }
        String authkey = cookieService.createIpSession(userIp);
        baseResponse.setResult(authkey);

        return baseResponse;
    }

    @RequestMapping(value = "delUserCookie", method = RequestMethod.GET)
    @ApiOperation(value = "退出登录接口", notes = "退出登录接口")
    public BaseResponse<String> delUserCookie(@RequestParam("userId") Long userId){
        BaseResponse<String> baseResponse = new BaseResponse();
        if (userId == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        cookieService.delUserSession(userId);
        UserAccountEO userAccountEO = new UserAccountEO();
        userAccountEO.setId(userId);
        userAccountEO.setLastLoginoutTime(new Date());
        userService.updateUserInfo(userAccountEO);
        return baseResponse;
    }
}
