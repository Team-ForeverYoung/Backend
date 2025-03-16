package com.java.backend.global.response;

import org.springframework.http.HttpStatus;

public class RestApiResponse implements ResponseApiCode{

	private final HttpStatus status;
	private final String code;
	private final String message;
	private Object data;

	public RestApiResponse(ResponseApiCode responseApiCode, Object data){
		this.status = responseApiCode.getStatus();
		this.code = responseApiCode.getCode();
		this.message=responseApiCode.getMessage();
		this.data = data;
	}
	public RestApiResponse(ResponseApiCode responseApiCode){ //response data가 필요없는경우
		this.status = responseApiCode.getStatus();
		this.code = responseApiCode.getCode();
		this.message=responseApiCode.getMessage();
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
