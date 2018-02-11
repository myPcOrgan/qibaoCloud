package com.qibao.frontend.config;

import com.qibao.frontend.interceptor.AuthInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Autowired
    AuthInInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //用户忽略拦截url集合
       this.addUrlList();
        // 多个拦截器组成一个拦截器链, addPathPatterns 用于添加拦截规则,excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加忽略拦截url
     */
    private void addUrlList(){

        List<String> urlList = Arrays.asList(
                "/api/box/selectBoxList",
                "/api/prize/selectPrizeList",
                "/api/userPrize/selectRecentlyBoxPrizeList",
                "/api/userPrize/selectBoxPrizeCount",
                "/api/userPrize/selectAllConsumeRoomPrizeList",
                "/api/userPrize/selectWeekConsumeRoomPrizeList",
                "/api/userPrize/selectRecentlyRoomPrizeList",
                "/api/image/getImage",
                "/api/image/setImage",
                "/api/login/actionStart",
                "/api/login/sendLoginVerifyCode",
                "/api/login/checkLoginVerifyCode",
                "/api/order/tradeSuccess",
                "/api/order/mallReturn",
                "/api/order/mallTradeSuccess",
                "/api/room/selectRoomList",
                "/api/goods/getGoodsById",
                "/api/goods/getGoodsInfos",
                "/api/userActivity/userPrizeList",
                "/api/userActivity/selectActivityUsers"
        );
        loginInterceptor.setUrlList(urlList);
    }




}