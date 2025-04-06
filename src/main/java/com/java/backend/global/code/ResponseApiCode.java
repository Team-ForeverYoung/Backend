package com.java.backend.global.code;

import org.springframework.http.HttpStatus;

public interface ResponseApiCode {
	HttpStatus getStatus();
	String getCode();
	String getMessage();
}