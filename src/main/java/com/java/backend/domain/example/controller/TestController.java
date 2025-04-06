package com.java.backend.domain.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.backend.domain.example.code.SuccessCode;
import com.java.backend.domain.example.dto.TestUserInfoResponseDto;
import com.java.backend.domain.example.dto.TestUserRegisterRequestDto;
import com.java.backend.domain.example.service.TestService;
import com.java.backend.global.response.RestApiResponse;
@RestController
@RequestMapping("api/v1/test")
public class TestController {

	private final TestService testService;

	public TestController(TestService testService) {
		this.testService = testService;
	}

	@GetMapping
	public String test(){
		return "test";
	}

	@PostMapping
	public ResponseEntity<RestApiResponse> test2(@RequestBody TestUserRegisterRequestDto testUserRegisterRequestDto){
		testService.registrationUser(testUserRegisterRequestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<RestApiResponse> test3(@PathVariable("userId") Long userId){
		TestUserInfoResponseDto response = testService.showUserInformation(userId);
		RestApiResponse data = new RestApiResponse(SuccessCode.USER_INFO_FOUND_OK,response);
		return ResponseEntity.ok(data);
	}
}
