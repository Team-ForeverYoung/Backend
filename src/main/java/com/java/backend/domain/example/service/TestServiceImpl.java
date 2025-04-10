package com.java.backend.domain.example.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.example.dto.TestUserInfoResponseDto;
import com.java.backend.domain.example.dto.TestUserRegisterRequestDto;
import com.java.backend.domain.example.entity.TestUser;
import com.java.backend.domain.example.repository.TestUserRepository;

@Service
public class TestServiceImpl implements TestService{
	private final TestUserRepository testUserRepository;

	public TestServiceImpl(TestUserRepository testUserRepository) {
		this.testUserRepository = testUserRepository;
	}

	@Override
	public void registrationUser(TestUserRegisterRequestDto testUserRegisterRequestDto) {
		TestUser newUser = createUser(testUserRegisterRequestDto);
		testUserRepository.save(newUser);
	}

	@Override
	public TestUserInfoResponseDto showUserInformation(Long productId) {
		return testUserRepository.findUserById(productId);
	}

	private TestUser createUser(TestUserRegisterRequestDto testUserRegisterRequestDto){
		return TestUser.builder().age(testUserRegisterRequestDto.getAge()).name(testUserRegisterRequestDto.getName()).build();
	}
}



