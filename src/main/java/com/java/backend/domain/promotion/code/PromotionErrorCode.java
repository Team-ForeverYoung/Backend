package com.java.backend.domain.promotion.code;


import org.springframework.http.HttpStatus;

import com.java.backend.global.code.ResponseApiCode;

public enum PromotionErrorCode implements ResponseApiCode {
	THIS_EVENT_IS_SOLD_OUT(HttpStatus.BAD_REQUEST,"e1001","amount가 0 이하 입니다(소진)"),
	EVENT_NOT_FOUND(HttpStatus.BAD_REQUEST,"e1002","해당 아이디에 대응되는 Event가 존재하지 않습니다."),
	COUPON_NOT_FOUND(HttpStatus.BAD_REQUEST,"c1001","해당 아이디에 대응되는 Coupon이 존재하지 않습니다."),
	USER_EVENT_NOT_FOUND(HttpStatus.BAD_REQUEST,"ue1001","해당 아이디에 대응되는 UserEvent가 존재하지 않습니다."),
	EVENT_DUPLICATED_JOIN(HttpStatus.BAD_REQUEST, "ue1002","해당 이벤트에 이미 참여한 유저 입니다.")
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
