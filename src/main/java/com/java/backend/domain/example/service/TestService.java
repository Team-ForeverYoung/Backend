package com.java.backend.domain.example.service;

import com.java.backend.domain.example.dto.UserInfoResponseDto;
import com.java.backend.domain.example.dto.UserRegisterRequestDto;

public interface TestService {
	void registrationUser(UserRegisterRequestDto userRegisterRequestDto);
	UserInfoResponseDto showUserInformation(Long productId);
}
