package com.qibao.backend;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableApolloConfig
@MapperScan(basePackages = "com.qibao.backend.mapper")
@EnableSwagger2Doc
public class BackendServer {

    public static void main(String[] args) {
        SpringApplication.run(BackendServer.class,args);
    }
}
