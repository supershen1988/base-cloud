package com.supershen.pro;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.supershen.pro.filters.ApiZuulFilter;

/**
 * 网关服务启动类
 * @author gshen
 *
 */
//redis 分布式session
//@EnableRedisHttpSession 
@SpringBootApplication
@EnableZuulProxy
public class App 
{
	/**
	 * 配置自定义zuul拦截器
	 */
	@Bean
	public ApiZuulFilter apiZuulFilter(){
		return new ApiZuulFilter();
	}
	
    public static void main(String[] args) throws Exception {
    	new SpringApplicationBuilder(App.class).web(true).run(args);
	}
    
    
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 允许cookies跨域
        config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
        config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.addAllowedMethod("*");// 允许提交请求的方法，*表示全部允许
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
