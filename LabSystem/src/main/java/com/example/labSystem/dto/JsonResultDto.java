package com.example.labSystem.dto;

import com.google.gson.GsonBuilder;

/**
 * 
 * @ClassName: JsonResultDto
 * @Description:MVC格式，返回json使用
 * @author wangzhenjiang 18524279914@163.com
 * @date 2017年3月16日 下午2:09:14
 *
 */
public class JsonResultDto {
	public static final String CODE_OK = "200";
	public static final String THIRDPARTY_CODE_OK = "100";
	public static final String THIRDPARTY_ERROR = "101";
	public static final String CODE_ERROR = "500";// 错误
	/*
	 * 状态
	 */
	private String code = CODE_OK;
	/*
	 * 消息
	 */
	private String message;
	/*
	 * 三方消息
	 */
	private String msg;
	/*
	 * 用户数据
	 */
	private Object data;

	/**
	 * 
	 * 方法用途: 把对象转换为json格式字符串<br>
	 * 实现步骤: <br>
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		return gb.create().toJson(this);
	}
	
	public JsonResultDto() {
		super();
	}

	public JsonResultDto(String code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public JsonResultDto(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public JsonResultDto(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
