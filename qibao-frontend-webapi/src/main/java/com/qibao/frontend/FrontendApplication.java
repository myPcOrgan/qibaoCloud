package com.qibao.frontend;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.didispace.swagger.EnableSwagger2Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableApolloConfig
@SpringBootApplication
@EnableEurekaClient
@EnableWebMvc
@EnableAsync
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2Doc
public class FrontendApplication {
	private static Logger logger = LoggerFactory.getLogger(FrontendApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(FrontendApplication.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		for (String profile : activeProfiles) {
			logger.warn("Spring Boot 使用profile为:{}" , profile);
		}
	}
}
