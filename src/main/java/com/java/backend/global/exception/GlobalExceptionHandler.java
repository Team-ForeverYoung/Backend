package com.java.backend.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.java.backend.global.code.RestApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<RestApiResponse> handleBusinessException(BusinessException e){
		ExceptionMetaData exceptionMetaData = e.getExceptionMetaData();
		RestApiResponse restApiResponse = new RestApiResponse(exceptionMetaData.getResponseApiCode());
		return ResponseEntity.status(exceptionMetaData.getResponseApiCode().getStatus()).body(restApiResponse);
	}
}
