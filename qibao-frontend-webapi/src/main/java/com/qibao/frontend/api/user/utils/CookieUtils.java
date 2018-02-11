package com.qibao.frontend.api.user.utils;

import com.alibaba.fastjson.JSONObject;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.DESHelper;
import com.qibao.common.utils.IpContext;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    private static final String DESKEY = "Y@j9^8Jv_cQW$45TrYv2UiWvO!";


    public static String saveCookie(HttpServletRequest request, HttpServletResponse response,JSONObject jsonObject) {

        String tokenParam = getTokenPara(jsonObject);
        try {
            tokenParam = URLEncoder.encode(tokenParam, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(01,"cookie encode error");
        }
        Cookie cookie = getCookie(request,CommonConstants.TOKEN_NAME);
        if (cookie != null) {
            cookie.setValue(tokenParam);
        } else {
            // 重新new一个Cookie
            cookie = new Cookie(CommonConstants.TOKEN_NAME, tokenParam);
        }
        cookie.setPath("/");// 同一个域名所有url cookie共享
        cookie.setMaxAge(24*60*60);// 24小时后失效
        response.addCookie(cookie);
        return tokenParam;
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i];
                }
            }
        }
        return null;
    }

    public static JSONObject cookieToObject(Cookie cookie){
        String value = cookie.getValue();
        String decrypt = null;
        try {
            value = URLDecoder.decode(value, "UTF-8");
            decrypt = DESHelper.decrypt(value, DESKEY);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(01,"cookie转换异常");
        } catch (Exception e) {
            throw new BaseException(01,"des解码异常");
        }

        if (StringUtils.isNotBlank(decrypt)) {
            JSONObject jsonObject = JSONObject.parseObject(decrypt);
            return jsonObject;
        }
        return null;
    }

    private static String getTokenPara(JSONObject jsonObject){
        String encrypt = null;
        try {
            encrypt = DESHelper.encrypt(jsonObject.toString(), DESKEY);
        } catch (Exception e) {
            throw new BaseException(01,"des加密异常");
        }
        return encrypt;
    }

    public static void deletePubCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookieUser = getCookie(request, CommonConstants.TOKEN_NAME);
        if (cookieUser != null) {
            cookieUser.setMaxAge(0);
            cookieUser.setPath("/");
            response.addCookie(cookieUser);
        }

        Cookie cookSessions = getCookie(request, CommonConstants.ONLY_ONE);
        if (cookSessions != null) {
            cookSessions.setMaxAge(0);
            cookSessions.setPath("/");
            response.addCookie(cookSessions);
        }

        Cookie cookieIp = getCookie(request, CommonConstants.PARAMS_SESSIONID);
        if (cookieIp != null) {
            cookieIp.setMaxAge(0);
            response.addCookie(cookieIp);
        }

        IpContext.clean();
        UserContext.clean();
    }

    /**
     *
     * <p>清理上下文信息</p>
     * @author ztjie
     * @date 2015-11-5 下午1:11:06
     * @see
     */
    public static void cleanContext(){
        IpContext.clean();
        UserContext.clean();
    }


}
