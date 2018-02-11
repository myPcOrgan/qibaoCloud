package com.qibao.backend.config;

import com.qibao.backend.interceptor.AuthInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Autowired
    AuthInInterceptor loginInterceptor;
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //用户忽略拦截url集合
       this.addUrlList();
       //用户登录与不登录都可以
       this.addDoubleUrlList();
        // 多个拦截器组成一个拦截器链, addPathPatterns 用于添加拦截规则,excludePathPatterns 用户排除拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加忽略拦截url
     */
    private void addUrlList(){
        List<String> urlList = new ArrayList(){
            {
                add("/backend/login");
                add("/backend/gold/queryGoldBalanceList");
                add("/backend/gold/exportGoldBalance");
                add("/backend/order/queryOrderList");
                add("/backend/order/exportOrderList");
                add("/api/image/uploadImage");
            }
        };
        loginInterceptor.setUrlList(urlList);
    }

    /**
     * 用户登录与不登录都可以
     */
    private void addDoubleUrlList(){
        List<String> urlList = new ArrayList(){
            {
            }
        };
        loginInterceptor.setDoubleUrlList(urlList);
    }



}