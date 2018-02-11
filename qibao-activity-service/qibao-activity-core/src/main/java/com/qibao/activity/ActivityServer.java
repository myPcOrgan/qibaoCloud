package com.qibao.activity;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@EnableApolloConfig
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan(basePackages = "com.qibao.activity.mapper")
public class ActivityServer {

    public static void main(String[] args) {
        SpringApplication.run(ActivityServer.class,args);
    }
}
