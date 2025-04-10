package com.java.backend.domain.promotion.exception;

import com.java.backend.domain.promotion.code.PromotionErrorCode;
import com.java.backend.global.exception.BusinessException;
import com.java.backend.global.exception.ExceptionMetaData;

public abstract class EventException extends BusinessException {

	public EventException(ExceptionMetaData exceptionMetaData) {
		super(exceptionMetaData);
	}

	public static class eventSoledOutException extends EventException{
		public eventSoledOutException(ExceptionMetaData exceptionMetaData) {
			super(exceptionMetaData);
		}
	}

	public static class eventNotFoundException extends EventException{
		public eventNotFoundException(ExceptionMetaData exceptionMetaData) {
			super(exceptionMetaData);
		}
	}

}
