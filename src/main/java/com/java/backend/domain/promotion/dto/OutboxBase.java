package com.java.backend.domain.promotion.dto;

import com.java.backend.global.kafka.OutBoxStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OutboxBase {
	private Long id;
	private Long createdAt;
	private String messageKey;
	private String payload;
	private OutBoxStatus status;
	private String topic;
}
