package com.java.backend.domain.example.code;

import org.springframework.http.HttpStatus;

import com.java.backend.global.response.ResponseApiCode;

public enum SuccessCode implements ResponseApiCode {
	USER_INFO_FOUND_OK(HttpStatus.OK,"S001","유저 정보 조회 성공!!!!");

	private final HttpStatus status;
	private final String code;
	private final String message;

	SuccessCode(HttpStatus status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
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
}
