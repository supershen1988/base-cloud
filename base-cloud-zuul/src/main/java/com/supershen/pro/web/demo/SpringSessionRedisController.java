package com.supershen.pro.web.demo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.supershen.pro.core.session.CacheUserInfo;

/**
 * spring session demo【 分布式session策略，存储采用redis】
 * 
 * @author gshen
 *
 */
@RestController
@RequestMapping("/demo/redisSession")
public class SpringSessionRedisController {
	private String redisTonken = "redisToken";

	@Resource
	private RedisTemplate<String, CacheUserInfo> redisTemplate;

	@ResponseBody
	@RequestMapping(value = "/session")
	public Map<String, Object> getSession(HttpServletRequest request) {
		request.getSession().setAttribute("username", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", request.getSession().getId());
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/get")
	public String get(HttpServletRequest request) {
		// CacheUserInfo user = UserHelper.getCurrentUser();
		// System.out.println("获取token[" + user.getRedisToken() + "]....");
		// CacheUserInfo user1 = (CacheUserInfo)
		// request.getSession().getAttribute(user.getRedisToken());
		// //System.out.println("user:" + user1.getNickname() + "....");
		return (String) request.getSession().getAttribute("username");
	}

	@ResponseBody
	@RequestMapping(value = "/getUser")
	public CacheUserInfo getUser(HttpServletRequest request) {
		Boolean result = redisTemplate.opsForHash().hasKey(redisTonken, "20f667e63c5b48f783b9e4d1ebee9b5f");
		if(result){
			System.out.println("用户存在");
		}else{
			System.out.println("用户不存在");
		}
		
		CacheUserInfo user =  (CacheUserInfo) redisTemplate.opsForHash().get(redisTonken, "20f667e63c5b48f783b9e4d1ebee9b5f");
		//CacheUserInfo user1 = (CacheUserInfo) request.getSession().getAttribute("7987c6b953ba463590a7bd7f86a8b67f");
		System.out.println("user:" + user.toString() + "....");
		return user;
	}

}
