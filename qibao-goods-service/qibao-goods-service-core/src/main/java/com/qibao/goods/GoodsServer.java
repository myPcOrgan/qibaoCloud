package com.qibao.goods;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableApolloConfig
@MapperScan(basePackages = "com.qibao.goods.mapper")
public class GoodsServer {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServer.class,args);
    }
}
