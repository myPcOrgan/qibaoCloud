package com.qibao.order;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by 339939 on 2018/1/15.
 */
@EnableApolloConfig
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2Doc
@EnableTransactionManagement
@MapperScan(basePackages = "com.qibao.order.mapper")
public class OrderServer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(OrderServer.class,args);
    }
}
