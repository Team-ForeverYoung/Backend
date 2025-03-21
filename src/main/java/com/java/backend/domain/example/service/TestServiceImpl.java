package com.java.backend.domain.example.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.example.dto.UserInfoResponseDto;
import com.java.backend.domain.example.dto.UserRegisterRequestDto;
import com.java.backend.domain.example.entity.User;
import com.java.backend.domain.example.repository.UserRepository;

@Service
public class TestServiceImpl implements TestService{
	private final UserRepository userRepository;

	public TestServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void registrationUser(UserRegisterRequestDto userRegisterRequestDto) {
		User newUser = createUser(userRegisterRequestDto);
		userRepository.save(newUser);
	}

	@Override
	public UserInfoResponseDto showUserInformation(Long productId) {
		return userRepository.findUserById(productId);
	}

	private User createUser(UserRegisterRequestDto userRegisterRequestDto){
		return User.builder().age(userRegisterRequestDto.getAge()).name(userRegisterRequestDto.getName()).build();
	}
}



