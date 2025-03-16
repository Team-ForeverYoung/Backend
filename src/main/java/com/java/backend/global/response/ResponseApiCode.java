package com.java.backend.global.response;

import org.springframework.http.HttpStatus;

public interface ResponseApiCode {
	HttpStatus getStatus();
	String getCode();
	String getMessage();
}
