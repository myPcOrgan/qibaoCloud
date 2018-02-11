package com.qibao.frontend.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.IpContext;
import com.qibao.common.utils.IpUtil;
import com.qibao.frontend.api.user.utils.CommonConstants;
import com.qibao.frontend.api.user.utils.CookieUtils;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.user.context.vo.UserAccountVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class AuthInInterceptor implements HandlerInterceptor {

    private List<String> urlList;

    protected static final Logger LOGGER = LoggerFactory.getLogger(AuthInInterceptor.class);



    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {
        String userIp = servletRequest.getHeader(CommonConstants.PARAMS_SESSIONID);
        if (StringUtils.isBlank(userIp)){
            userIp = IpUtil.getIpAddr(servletRequest);
        }
        IpContext.setCurrentContext(userIp);

        String pathInfo = servletRequest.getServletPath();
        LOGGER.info("用户url：" + pathInfo);
        if ("/error".equals(pathInfo)){
            throw new BaseException(01,"获取url异常");
        }
        Cookie cookie = CookieUtils.getCookie(servletRequest, CommonConstants.TOKEN_NAME);
        if (cookie != null){
            JSONObject jsonObject = CookieUtils.cookieToObject(cookie);
            if (jsonObject != null) {
                Object uid1 = jsonObject.get(CommonConstants.SERVICE_REQUEST_HEADER_UID);
                Object authKey1 = jsonObject.get(CommonConstants.SERVICE_REQUEST_HEADER_AUTHKEY);
                String uid = uid1 == null ? "" : uid1.toString();
                String authKey = authKey1 == null ? "" : authKey1.toString();
                if (StringUtils.isNotBlank(uid)) {
                    UserAccountVO result = new UserAccountVO();
                    result.setId(Long.parseLong(uid));
                    UserContext.setCurrentContext(result, authKey);
                }
            }
        } else {
            if (urlList != null && urlList.size() > 0){
                for (String url: urlList) {
                    if (url.equals(pathInfo)){
                       return true;
                    }
                }
            }
            throw new BaseException(01,"用户未登录");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CookieUtils.cleanContext();
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

}
