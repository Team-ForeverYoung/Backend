package com.java.backend.domain.example.service;

import com.java.backend.domain.example.dto.TestUserInfoResponseDto;
import com.java.backend.domain.example.dto.TestUserRegisterRequestDto;

public interface TestService {
	void registrationUser(TestUserRegisterRequestDto testUserRegisterRequestDto);
	TestUserInfoResponseDto showUserInformation(Long productId);
}
