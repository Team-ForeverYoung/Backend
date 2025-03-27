package com.java.backend.domain.example.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.example.dto.UserInfoResponseDto;
import com.java.backend.domain.example.dto.UserRegisterRequestDto;
import com.java.backend.domain.example.entity.TestUser;
import com.java.backend.domain.example.repository.UserRepository;

@Service
public class TestServiceImpl implements TestService{
	private final UserRepository userRepository;

	public TestServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void registrationUser(UserRegisterRequestDto userRegisterRequestDto) {
		TestUser newUser = createUser(userRegisterRequestDto);
		userRepository.save(newUser);
	}

	@Override
	public UserInfoResponseDto showUserInformation(Long productId) {
		return userRepository.findUserById(productId);
	}

	private TestUser createUser(UserRegisterRequestDto userRegisterRequestDto){
		return TestUser.builder().age(userRegisterRequestDto.getAge()).name(userRegisterRequestDto.getName()).build();
	}
}



