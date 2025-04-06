package com.java.backend.domain.promotion.code;

import org.springframework.http.HttpStatus;

import com.java.backend.global.code.ResponseApiCode;

public enum PromotionCode implements ResponseApiCode {

	COUPON_CREATED_SUCCESS(HttpStatus.CREATED,"c001","쿠폰이 성공적으로 등록 되었습니다."),
	EVENT_CREATED_SUCCESS(HttpStatus.CREATED,"e001","이벤트가 성공적으로 등록 되었습니다."),
	USER_EVENT_CREATED_SUCCESS(HttpStatus.CREATED,"ue001","이벤트에 성공적으로 참여 했습니다"),
	;

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
	PromotionCode(HttpStatus httpStatus, String code, String message) {
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
