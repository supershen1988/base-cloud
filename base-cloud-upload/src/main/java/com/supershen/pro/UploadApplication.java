package com.supershen.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 上传文件微服务
 * @author gshen
 *
 */
//注册中心客户端标识
@EnableDiscoveryClient
//SpringBoot 应用标识
@SpringBootApplication
public class UploadApplication
{
    public static void main( String[] args )
    {
    	SpringApplication.run(UploadApplication.class, args);
    }
}
