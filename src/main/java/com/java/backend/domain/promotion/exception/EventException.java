package com.java.backend.domain.promotion.exception;

import com.java.backend.global.exception.BusinessException;
import com.java.backend.global.exception.ExceptionMetaData;

public abstract class EventException extends BusinessException {

	public EventException(ExceptionMetaData exceptionMetaData) {
		super(exceptionMetaData);
	}

	public static class eventSoldedOutException extends EventException{
		public eventSoldedOutException(ExceptionMetaData exceptionMetaData) {
			super(exceptionMetaData);
		}
	}

}
