package com.java.backend.global.exception;

import org.springframework.http.HttpStatus;

import com.java.backend.global.code.ResponseApiCode;

import lombok.Builder;

public class ExceptionMetaData {
	private static final String NO_STACKTRACE = "NO_STACKTRACE_IN_THIS_EXCEPTION";
	private static final String NO_CLASS_NAME = "NO_CLASS_NAME";
	private static final ResponseApiCode NO_RESPONSE_API_CODE = ExceptionMetaDataErrorCode.HAS_NO_RESPONSE_API_CODE;

	private String className;
	private String stackTrace;
	private ResponseApiCode responseApiCode;

	@Builder
	public ExceptionMetaData(String className, String stackTrace, ResponseApiCode responseApiCode) {
		this.className = className != null ? className : NO_CLASS_NAME;
		this.stackTrace = stackTrace != null ? stackTrace : NO_STACKTRACE;
		this.responseApiCode = responseApiCode != null ? responseApiCode : NO_RESPONSE_API_CODE;
	}

	@Builder
	public ExceptionMetaData(ResponseApiCode responseApiCode) {
		this.responseApiCode = responseApiCode != null ? responseApiCode : NO_RESPONSE_API_CODE;
	}

	public String getClassName() {
		return className;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public ResponseApiCode getResponseApiCode() {
		return responseApiCode;
	}

	enum ExceptionMetaDataErrorCode implements ResponseApiCode {
		HAS_NO_RESPONSE_API_CODE(HttpStatus.NOT_FOUND, "E001", "예외코드가 입력되지 않은 예외 입니다.");
		private final HttpStatus status;
		private final String code;
		private final String message;

		ExceptionMetaDataErrorCode(HttpStatus status, String code, String message) {
			this.status = status;
			this.code = code;
			this.message = message;
		}

		@Override
		public HttpStatus getStatus() {
			return this.status;
		}

		@Override
		public String getCode() {
			return this.code;
		}

		@Override
		public String getMessage() {
			return this.message;
		}
	}
}
