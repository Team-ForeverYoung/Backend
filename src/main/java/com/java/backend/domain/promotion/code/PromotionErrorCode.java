package com.java.backend.domain.promotion.code;


import org.springframework.http.HttpStatus;

import com.java.backend.global.code.ResponseApiCode;

public enum PromotionErrorCode implements ResponseApiCode {
	THIS_EVENT_IS_SOLD_OUT(HttpStatus.BAD_REQUEST,"e1001","amount가 0 이하 입니다(소진)")
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
	PromotionErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}
	@Override
	public HttpStatus getStatus() {
		return httpStatus;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
