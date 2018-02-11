package com.qibao.frontend.api.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.IpContext;
import com.qibao.common.utils.IpUtil;
import com.qibao.frontend.api.user.utils.CommonConstants;
import com.qibao.frontend.api.user.utils.CookieUtils;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.ISysMessage;
import com.qibao.frontend.feign.IUserLoginFeign;
import com.qibao.user.context.dto.MessageDTO;
import com.qibao.user.context.dto.SendMessageDTO;
import com.qibao.user.context.enums.SysMessageTypeEnum;
import com.qibao.user.context.vo.SessionVO;
import com.qibao.user.context.vo.ThirdLoginVO;
import com.qibao.user.context.vo.UserAccountInfoVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/login")
public class UserLoginController extends BaseController {


    @Autowired
    IUserLoginFeign userLoginFeign;

    @Autowired
    ISysMessage sysMessage;

    @RequestMapping(value = "sendLoginVerifyCode", method = RequestMethod.GET)
    public BaseResponse sendPhoneVerifyCode(@RequestParam("mobilePhone") String phone){
        BaseResponse<String> baseResponse;
        String userIp = IpContext.getUserIp();
        if (StringUtils.isBlank(userIp)){
            userIp = "1";
        }
        if (StringUtils.isBlank(phone)){
            throw new BaseException(01,"用户手机号不能为空");
        }
        if (!phone.matches("^1(3|4|5|7|8)\\d{9}$")){
            throw new BaseException(01,"用户手机号格式不正确");
        }
        String code = RandomStringUtils.randomNumeric(4);
        String context = "【7Bao网】验证码" + code + "，您正在进行登录身份验证，打死不要告诉别人哦！";
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setCode(code);
        sendMessageDTO.setContext(context);
        sendMessageDTO.setPhone(phone);
        sendMessageDTO.setUserIp(userIp);
        //发送短信
        LOGGER.info("调用发送接口" + phone + ";code:" + code);
        baseResponse = userLoginFeign.sendPhoneVerifyCode(sendMessageDTO);
        LOGGER.info("发送成功" + phone);

        return baseResponse;
    }

    @RequestMapping(value = "checkLoginVerifyCode", method = RequestMethod.GET)
    public BaseResponse<UserAccountInfoVO> checkPhoneVerifyCode(@RequestParam("mobilePhone") String phone,
                                             @RequestParam("verifyCode") String code){
        BaseResponse<UserAccountInfoVO> baseResponse1 = new BaseResponse();
        String userIp = IpContext.getUserIp();
        if (StringUtils.isBlank(userIp)){
            userIp = "1";
        }
        if (StringUtils.isBlank(phone)){
            throw new BaseException(01,"用户手机号不能为空");
        }
        if (StringUtils.isBlank(code)){
            throw new BaseException(01,"验证码不能为空");
        }
        LOGGER.info("验证验证码接口" + phone + ";code:" + code);
        BaseResponse<ThirdLoginVO> baseResponse = userLoginFeign.checkPhoneVerifyCode(phone, code, userIp);
        LOGGER.info("验证验证码接口成功" + baseResponse);
        if (baseResponse != null && "00".equals(baseResponse.getCode())){
            String authkey = baseResponse.getResult().getUrl();
            Long userId = baseResponse.getResult().getId();
            if ("03".equals(authkey)){
                throw new BaseException(01,"验证码错误");
            }
            int messageNum = this.getUserSysMessageNum(userId);
            ThirdLoginVO thirdLoginVO = baseResponse.getResult();
            UserAccountInfoVO userAccountInfoVO = new UserAccountInfoVO();
            BeanMapper.copyProperties(thirdLoginVO,userAccountInfoVO);
            userAccountInfoVO.setUserId(thirdLoginVO.getId());
            userAccountInfoVO.setEmailNum(messageNum);
            userAccountInfoVO.setTotalGold(thirdLoginVO.getTotalAmount());
            baseResponse1.setResult(userAccountInfoVO);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(CommonConstants.SERVICE_REQUEST_HEADER_UID,userId.toString());
            jsonObject.put(CommonConstants.SERVICE_REQUEST_HEADER_AUTHKEY,authkey);
            // 根据session生成cookie
            CookieUtils.saveCookie(request,response, jsonObject);
        }

        return baseResponse1;
    }

    @RequestMapping(value = "actionStart", method = RequestMethod.GET)
    public BaseResponse actionStart(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.error("开始访问actionStart");
        BaseResponse<SessionVO> baseResponse = new BaseResponse<>();
        String sessionId = "";
        String ip = IpUtil.getIpAddr(request);
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        LOGGER.error("用户IP" + ip);
        BaseResponse<String> oldBaseResponse = userLoginFeign.saveIpSession(ip);
        if (baseResponse != null && "00".equals(baseResponse.getCode())){
            sessionId = oldBaseResponse.getResult().toString();
        }
        Cookie cookie = new Cookie(CommonConstants.PARAMS_SESSIONID, sessionId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);// 60分钟失效
        response.addCookie(cookie);
        SessionVO sessionVO = new SessionVO();
        sessionVO.setSid(sessionId);
        baseResponse.setResult(sessionVO);
        LOGGER.error("用户访问结束");
        return baseResponse;
    }


    @RequestMapping(value = "loginOut", method = RequestMethod.GET)
    public BaseResponse loginOut(HttpServletRequest request, HttpServletResponse response) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        Cookie cookie = CookieUtils.getCookie(request, CommonConstants.TOKEN_NAME);
        if (cookie != null) {
            Long currentUserId = UserContext.getCurrentUserId();
            //删除redis中的cookie
            if (currentUserId != null) {
                baseResponse = userLoginFeign.delUserCookie(currentUserId);
            }
            // 失效Cookie
            CookieUtils.deletePubCookie(request, response);
        }

        return baseResponse;
    }


    private int getUserSysMessageNum(Long userId){
        //获取系统消息数
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserId(userId);
        messageDTO.setMessageType(SysMessageTypeEnum.SYSTEM.getCode());
        messageDTO.setIsView(false);
        BaseResponse<Integer> baseResponse = sysMessage.countSysMessage(messageDTO);
        Integer result = baseResponse.getResult();
        return result;
    }




}
