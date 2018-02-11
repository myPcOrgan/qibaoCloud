package com.qibao.backend.interceptor;


import com.qibao.backend.common.Constants;
import com.qibao.backend.common.CookieUtils;
import com.qibao.backend.common.UserContext;
import com.qibao.backend.model.UserVO;
import com.qibao.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/24.
 */
@Component
public class AuthInInterceptor implements HandlerInterceptor {

    private List<String> urlList;

    private List<String> doubleUrlList;

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {
        //换成从服务层获取IP
//        String userIp = servletRequest.getHeader(Constants.HEADER_IP_KEY);
//        if (StringUtils.isBlank(userIp)){
//            userIp = IpUtil.getIpAddr(servletRequest);
//        }
//        IpContext.setCurrentContext(userIp);
        String pathInfo = servletRequest.getServletPath();
        Cookie cookie = CookieUtils.getCookie(servletRequest, Constants.COOKIE_NAME);
        if (cookie != null) {
            CookieUtils.cookieToSession(cookie, servletRequest.getSession());
            String userAccount = servletRequest.getSession().getAttribute(Constants.SERVICE_REQUEST_HEADER_ACCOUNT).toString();
            if (StringUtils.isNotBlank(userAccount)) {
                UserVO user = new UserVO();
                user.setLoginAccount(userAccount);
                UserContext.setCurrentUser(user);
            }
            return true;
        }
        if (urlList != null && urlList.size() > 0) {
            for (String url : urlList
                    ) {
                if (url.equals(pathInfo)) {
                    return true;
                }
            }
        }
        throw new BaseException(1, "用户未登录");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String pathInfo = request.getServletPath();
        if (pathInfo.equals("/backend/logout")) {
            CookieUtils.deleteCookie(request, response);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getDoubleUrlList() {
        return doubleUrlList;
    }

    public void setDoubleUrlList(List<String> doubleUrlList) {
        this.doubleUrlList = doubleUrlList;
    }
}
