package com.supershen.pro.core.session;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Objects;

/**
 * 分布式redis缓存保存的session 用户信息
 * 
 * @author gshen
 *
 */
public class CacheUserInfo implements Serializable {

	private static final long serialVersionUID = 8931071646931941762L;

	private Integer id;

	private String username;

	private String nickname;

	private String password;

	private String redisToken;
	/** token创建时间*/
	private Date expiredTime;
	/** 用户类型，1:web客户端，2:手机客户端*/
	private Integer userType = 1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedisToken() {
		return redisToken;
	}

	public void setRedisToken(String redisToken) {
		this.redisToken = redisToken;
	}
	
	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public CacheUserInfo(Integer id, String username, String nickname, String password, String redisToken) {
		super();
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.redisToken = redisToken;
	}

	public CacheUserInfo(Integer id, String username, String nickname, String redisToken, Date expiredTime) {
		super();
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.redisToken = redisToken;
		this.expiredTime = expiredTime;
	}

	public CacheUserInfo() {
		super();
	}


	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return username;
	}

	/**
	 * 重载hashCode,只计算username;
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(username);
	}

	/**
	 * 重载equals,只计算loginName;
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CacheUserInfo other = (CacheUserInfo) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}

		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
