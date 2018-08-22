package com.supershen.pro.core.api;

import com.supershen.pro.core.constants.Constants;

/**
 * 服务端返回信息类
 * @author gshen
 * @创建时间 2018-07-12 19:07
 * @copyright © 2018 哈尔滨研石科技有限公司版权所有
 */
public class BackMessage {
	private int code;
	private String message;
	// 按照模块定义BackMessage
	// 通用异常
	public static BackMessage SUCCESS = new BackMessage(0,"success");
	public static BackMessage SERVER_EXCEPTION = new BackMessage(500100,"服务端异常");
	public static BackMessage PARAMETER_ISNULL = new BackMessage(500101,"输入参数为空");
	// 业务异常
	public static BackMessage USER_NOT_EXSIST = new BackMessage(500102,"用户不存在"); 
	public static BackMessage ONLINE_USER_OVER = new BackMessage(500103,"在线用户数超出允许登录的最大用户限制。"); 
	public static BackMessage SESSION_NOT_EXSIST =  new BackMessage(500104,"不存在离线session数据");
	public static BackMessage NOT_FIND_DATA = new BackMessage(500105,"查找不到对应数据");
	
	public BackMessage() {
		
	}
	
	private BackMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static BackMessage success(String moduleName,String action) {
		if(moduleName == null) {
			moduleName = "";
		}
		
		return new BackMessage(0,moduleName+action+"成功");
	}

	/**
	 * 保存异常
	 * @param moduleName 模块名称
	 * @return
	 */
	public static BackMessage saveFault(String moduleName) {
		if(moduleName == null) {
			moduleName = "";
		}
		
		return new BackMessage(500106,moduleName+Constants.ACTION_ADD+"异常");
	}
	
	/**
	 * 修改异常
	 * @param moduleName 模块名称
	 * @return
	 */
	public static BackMessage updateFault(String moduleName) {
		if(moduleName == null) {
			moduleName = "";
		}
		
		return new BackMessage(500107,moduleName+Constants.ACTION_UPDATE+"异常");
	}
	
	/**
	 * 删除异常
	 * @param moduleName 模块名称
	 * @return
	 */
	public static BackMessage deleteFault(String moduleName) {
		if(moduleName == null) {
			moduleName = "";
		}
		
		return new BackMessage(500108,moduleName+Constants.ACTION_DELETE+"异常");
	}
	
	/**
	 * 不能删除返回类型
	 * @param reason 不能删除的原因
	 * @return BackMessage对象
	 */
	public static BackMessage cannotDeleteFault(String reason) {
		return new BackMessage(500109,reason);
	}
}
