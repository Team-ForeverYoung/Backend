package com.java.backend.global.exception;

public class BusinessException extends RuntimeException {
	private final ExceptionMetaData exceptionMetaData;
	public BusinessException(ExceptionMetaData exceptionMetaData) {
		super(exceptionMetaData.getResponseApiCode().getMessage());
		this.exceptionMetaData = exceptionMetaData;
	}

	public ExceptionMetaData getExceptionMetaData() {
		return exceptionMetaData;
	}
}
