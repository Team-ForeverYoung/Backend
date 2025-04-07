package com.java.backend.domain.promotion.exception;

import com.java.backend.global.exception.BusinessException;
import com.java.backend.global.exception.ExceptionMetaData;

public abstract class UserEventException extends BusinessException {

	public UserEventException(ExceptionMetaData exceptionMetaData) {
		super(exceptionMetaData);
	}

	public static class UserEventNotFoundException extends UserEventException {
		public UserEventNotFoundException(ExceptionMetaData exceptionMetaData) {
			super(exceptionMetaData);
		}
	}
}
