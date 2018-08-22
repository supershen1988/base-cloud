package com.supershen.pro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * 文件配置类
 * 
 * @author gshen
 * @创建时间 2018-07-23 11:07
 * @copyright © 2018
 */
@Configuration
public class FileConfig extends WebMvcConfigurerAdapter {

	 @Value("${ys.cloud.upload.path}")
	 private String location;

	/**
	 * 配置文件大小
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 文件最大KB,MB
		factory.setMaxFileSize("10MB");
		// 设置总上传数据总大小
		factory.setMaxRequestSize("50MB");
		return factory.createMultipartConfig();
	}

	/**
	 * 设置url指向服务器硬盘地址
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//图片回显
		registry.addResourceHandler("/imageShow/**").addResourceLocations("file:" + location);
		//文件
		registry.addResourceHandler("/fileShow/**").addResourceLocations("file:" + location);
		//registry.addResourceHandler("upload/imageShow/**").addResourceLocations("file:" + location);
		//registry.addResourceHandler("ys-cloud-upload/imageShow/**").addResourceLocations("file:" + location);
	}
}
