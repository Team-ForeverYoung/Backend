package com.java.backend.domain.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TestUserInfoResponseDto {
	private String name;
	private Integer age;

	public TestUserInfoResponseDto(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public Integer getAge() {
		return this.age;
	}
}
