package com.qibao.user;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.Ordered;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan(basePackages = "com.qibao.user.dao")
@EnableSwagger2Doc
@EnableDiscoveryClient
@EnableApolloConfig(order = Ordered.HIGHEST_PRECEDENCE)
public class UserServer {

    public static void main(String[] args) {
        SpringApplication.run(UserServer.class,args);
    }
}
