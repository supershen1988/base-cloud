package com.supershen.pro.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * http安全配置
 * @author gshen
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Resource
	public void configGloble(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("user").password("ysuser").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//表示所有访问必须认证处理才能进行
		http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
		//所有请求设置为session无状态
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//解决post无token无法提交问题 @see https://blog.csdn.net/shawearn1027/article/details/71119587
		http.csrf().disable();
	}
    
}
