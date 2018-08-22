package com.supershen.pro.core.api;

public class BackResult<T> {

	public int code;
	public String message;

	public BackResult() {
	}

	public BackResult(int code, String message) {
		this.code = code;
		this.message = message;
	}
	private T data;

	public BackResult(T data) {
		this.code = 0;
		this.message = "success";
		this.data = data;
	}
	
	public BackResult(Boolean result) {
		if(result){
			this.code = 0;
			this.message = "success";
			return;
		}
		
		BackMessage bm = BackMessage.SERVER_EXCEPTION;
		this.code = bm.getCode();
		this.message = bm.getMessage();
		
	}
    public BackResult(int code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
	private BackResult(BackMessage cm) {
		if(cm == null){
			return;
		}
		this.code = cm.getCode();
		this.message = cm.getMessage();
	}
    
    /**
     * app返回成功或者失败
     * @param result true/false
     * @param data 传递到app的数据
     * @param message 传递到app中的消息
     */
    @SuppressWarnings("unchecked")
    public static <T> BackResult<T> appResult(String message,T data,Boolean result) {
        if(result){
            return new BackResult<T>(0,message,data);
        }
        return new BackResult<T>(1,message,data);
    }
    
    
    
    
    /**
	 * 成功时候的调用
	 */
	public static <T> BackResult<T> success(T data) {
		return new BackResult<T>(data);
	}

	/**
	 * 成功，不需要传入参数
	 */
	@SuppressWarnings("unchecked")
	public static <T> BackResult<T> success() {
		return (BackResult<T>) success("");
	}
	
	/**
	 * 返回成功或者失败
	 * @param result true/false
	 */
	@SuppressWarnings("unchecked")
	public static <T> BackResult<T> result(Boolean result) {
		if(result){
			return (BackResult<T>) success("");
		}
		return (BackResult<T>) error(BackMessage.SERVER_EXCEPTION);
	}

	/**
	 * 失败时候的调用
	 */
	public static <T> BackResult<T> error(BackMessage cm) {
		return new BackResult<T>(cm);
	}

	/**
	 * 失败时候的调用,扩展消息参数
	 */
	public static <T> BackResult<T> error(BackMessage cm, String msg) {
		cm.setMessage(cm.getMessage() + "--" + msg);
		return new BackResult<T>(cm);
	}

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

}