package com.qibao.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * 启动 eureka发现服务
 * 启动 WebApplication服务
 * @author lijing
 *
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.qibao.goods.mapper")
public class CommonServer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CommonServer.class,args);
    }

}
