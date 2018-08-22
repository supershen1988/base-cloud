package com.supershen.pro.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.supershen.pro.core.session.CacheUserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 自定义网关拦截器-实现请求token认证
 * 
 * @author gshen
 *
 */
public class ApiZuulFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(ApiZuulFilter.class);
	
	private String redisTonken = "redisToken";
	
	@Resource
	private RedisTemplate<String,CacheUserInfo> redisTemplate;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info(String.format("发送  %s 请求到 %s,route路径是:%s", request.getMethod(), request.getRequestURL().toString(),request.getRequestURI().toString()));
		//如果是认证中心登录请求，直接跳转不做拦截
		if("/user-auth/login".equals(request.getRequestURI())
                || "/user-auth/app/login".equals(request.getRequestURI())
                || "/user-auth/app/checkToken".equals(request.getRequestURI())
                || "/user-auth/app/checkPhoneCode".equals(request.getRequestURI())
                || "/user-auth/app/getPhoneCode".equals(request.getRequestURI())
                || "/user-auth/app/save".equals(request.getRequestURI())
                || "/user-auth/app/logout".equals(request.getRequestURI())
                || "/user-auth/app/update".equals(request.getRequestURI())
                || "/system-service/sys/appupdate/checkUpdate".equals(request.getRequestURI())
                || "/user-auth/logout".equals(request.getRequestURI())){
			return null;
		}
  
		//过滤上传功能，不做拦截
		if(request.getRequestURI().startsWith("/upload/")
                || request.getRequestURI().startsWith("/zuul/upload/")){
			return null;
		}
		
		String token = request.getHeader("redisToken");
		logger.info("用户请求的token:【" +token+ "】");
		// String token = request.getParameter("token");
	     if(token == null) {
	         ctx.setSendZuulResponse(false);
	         ctx.setResponseStatusCode(401);
	         ctx.setResponseBody("{\"code\":\"401\",message:\"token cannot be empty\"}");
	         return null;
	     }
	     
	    CacheUserInfo user =  (CacheUserInfo) redisTemplate.opsForHash().get(redisTonken, token);
	    
	    if(user == null){
	    	ctx.setSendZuulResponse(false);
	        ctx.setResponseStatusCode(401);
	        ctx.setResponseBody("{\"code\":\"401\",message:\"token userinfo is null\"}");
	        return null;
	    }
	    //判断是否为客户端请求
	    if(user.getUserType() == 2){
	    	logger.info("用户信息:【" +user.toString()+ "】,登录为客户端登录");
	    	user.setExpiredTime(new Date());
	    	redisTemplate.opsForHash().put(redisTonken, token, user);
			redisTemplate.expire(redisTonken, 24, TimeUnit.HOURS);
	    }
	    logger.info("用户信息:【" +user.toString()+ "】");
		return null;
	}

}
