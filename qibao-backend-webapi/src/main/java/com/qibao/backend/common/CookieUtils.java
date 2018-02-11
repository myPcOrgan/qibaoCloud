package com.qibao.backend.common;

import com.alibaba.fastjson.JSONObject;

import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.DESHelper;
import com.qibao.common.utils.IpContext;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieUtils {

    private static final String DESKEY = "Y@j9^8Jv_cQW$45TrYv2UiWvO!";

    public static String saveCookie(HttpServletRequest request, HttpServletResponse response) {
        String tokenParam = getTokenPara(request.getSession());
        try {
            tokenParam = URLEncoder.encode(tokenParam, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(01, "cookie encode error");
        }
        Cookie cookie = getCookie(request, Constants.COOKIE_NAME);
        if (cookie != null) {
            cookie.setValue(tokenParam);
        } else {
            // 重新new一个Cookie
            cookie = new Cookie(Constants.COOKIE_NAME, tokenParam);
        }
        cookie.setPath("/");// 同一个域名所有url cookie共享
        cookie.setMaxAge(24 * 60 * 60);// 24小时后失效
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

    public static void cookieToSession(Cookie cookie, HttpSession session) {
        String value = cookie.getValue();
        String decrypt = null;
        try {
            value = URLDecoder.decode(value, "UTF-8");
            decrypt = DESHelper.decrypt(value, DESKEY);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(01, "cookie转换异常");
        } catch (Exception e) {
            throw new BaseException(01, "des解码异常");
        }

        if (StringUtils.isNotBlank(decrypt)) {
            JSONObject jsonObject = JSONObject.parseObject(decrypt);
            session.setAttribute(Constants.SERVICE_REQUEST_HEADER_ACCOUNT, jsonObject.get("userAccount"));
        }

    }

    private static String getTokenPara(HttpSession session) {
        String encrypt = "";
        String userAccount = (String) session.getAttribute(Constants.SERVICE_REQUEST_HEADER_ACCOUNT);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userAccount", userAccount);
        try {
            encrypt = DESHelper.encrypt(jsonObject.toString(), DESKEY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(01, "des加密异常");
        }
        return encrypt;
    }


    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie_token = getCookie(request, Constants.COOKIE_NAME);
        if (cookie_token != null) {
            cookie_token.setMaxAge(0);
            cookie_token.setPath("/");
            response.addCookie(cookie_token);
        }

        Cookie cookie_jsession = getCookie(request, Constants.PARAMS_SESSIONID);
        if (cookie_jsession != null) {
            cookie_jsession.setMaxAge(0);
            response.addCookie(cookie_jsession);
        }
        IpContext.clean();
        UserContext.clean();
    }


}
