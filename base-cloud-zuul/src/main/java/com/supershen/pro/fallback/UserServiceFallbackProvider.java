package com.supershen.pro.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 用户微服务回退配置
 * 
 * @author gshen
 *
 */
@Component
public class UserServiceFallbackProvider implements ZuulFallbackProvider {
	// 定义一个全局的记录器，通过LoggerFactory获取
	private final static Logger logger = LoggerFactory.getLogger(UserServiceFallbackProvider.class);

	/**
	 * 设置访问当个微服务出问题提供回退
	 */
	@Override
	public String getRoute() {
		return "base-cloud-user-service";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
			@Override
			public HttpHeaders getHeaders() {
				// headers设定
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}

			@Override
			public InputStream getBody() throws IOException {
				logger.debug("用户微服务不可用,请稍后再试。");
				// 响应体
				return new ByteArrayInputStream("用户微服务不可用,请稍后再试。".getBytes());
			}

			@Override
			public String getStatusText() throws IOException {
				// 状态文本，此处返的OK，详见HttpStatus
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				// fallback时的状态码
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				// 数字类型的状态码,此处返的200，详见HttpStatus
				return this.getStatusCode().value();
			}

			@Override
			public void close() {
			}
		};
	}

}
