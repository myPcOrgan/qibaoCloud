package com.qibao;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableApolloConfig
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableSwagger2Doc
@EnableTransactionManagement
@MapperScan(basePackages = "com.qibao.payment.mapper")
@EnableScheduling
public class PaymentService {

	public static void main(String[] args) {
		SpringApplication.run(PaymentService.class, args);
	}
}
