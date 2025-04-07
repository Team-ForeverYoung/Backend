package com.java.backend.domain.promotion.exception;

import com.java.backend.global.exception.BusinessException;
import com.java.backend.global.exception.ExceptionMetaData;

public abstract class CouponException extends BusinessException {

	public CouponException(ExceptionMetaData exceptionMetaData) {
		super(exceptionMetaData);
	}

	public static class couponNotFoundException extends CouponException{

		public couponNotFoundException(ExceptionMetaData exceptionMetaData) {
			super(exceptionMetaData);
		}
	}
}
