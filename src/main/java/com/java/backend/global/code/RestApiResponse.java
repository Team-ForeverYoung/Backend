package com.java.backend.global.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RestApiResponse implements ResponseApiCode {
	private HttpStatus status;
	private String code;
	private String message;
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

	public RestApiResponse() {

	}
}
