package com.zdjy.bigdata.antun.web.response;

/**
 * 统一的返回值
 * @author zdjy
 *
 */
public class BaseResponse {
	private Integer code;
	private String message;
	private Object data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
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
	public BaseResponse code(Integer code){
		this.code=code;
		return this;
	}
	public BaseResponse message(String message){
		this.message=message;
		return this;
	}
	public BaseResponse data(Object data){
		this.data=data;
		return this;
	}
	public static BaseResponse errorResponse(){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setCode(400);
		return baseResponse;
	}
	public static BaseResponse errorResponse(String messgae){
		BaseResponse errorResponse = errorResponse();
		errorResponse.setMessage(messgae);
		return errorResponse;
	}
	public static BaseResponse successResponse(){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setCode(200);
		return baseResponse;
	}
	public static BaseResponse successResponse(String messgae){
		BaseResponse errorResponse = successResponse();
		errorResponse.setMessage(messgae);
		return errorResponse;
	}
	
}