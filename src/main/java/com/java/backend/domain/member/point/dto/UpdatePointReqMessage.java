package com.java.backend.domain.member.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdatePointReqMessage {
	private int point;
	private Long userId;
}
