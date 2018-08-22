package com.supershen.pro.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 注册中心服务启动类
 * @author gshen
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(EurekaServerApplication.class)
        .web(true).run(args);
	}

}
