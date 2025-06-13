package com.java.backend.domain.member.point.dto;

import lombok.Getter;

@Getter
public class UserPoint {
	private final int point;

	public UserPoint(int point) {
		this.point = point;
	}
}
