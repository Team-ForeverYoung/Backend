package com.java.backend.domain.example.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.example.dto.UserInfoResponseDto;
import com.java.backend.domain.example.dto.UserRegisterRequestDto;
import com.java.backend.domain.example.entity.User;
import com.java.backend.domain.example.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService{
	private final TestRepository testRepository;

	public TestServiceImpl(TestRepository testRepository) {
		this.testRepository = testRepository;
	}

	@Override
	public void registrationUser(UserRegisterRequestDto userRegisterRequestDto) {
		User newUser = createUser(userRegisterRequestDto);
		testRepository.save(newUser);
	}

	@Override
	public UserInfoResponseDto showUserInformation(Long productId) {
		return testRepository.findUserById(productId);
	}

	private User createUser(UserRegisterRequestDto userRegisterRequestDto){
		return User.builder().age(userRegisterRequestDto.getAge()).name(userRegisterRequestDto.getName()).build();
	}
}



