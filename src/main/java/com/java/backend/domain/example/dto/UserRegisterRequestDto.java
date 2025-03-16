package com.java.backend.domain.example.dto;

public class UserRegisterRequestDto {
	private final String name;
	private final Integer age;

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public UserRegisterRequestDto(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
