package com.java.backend.domain.promotion.message;

import lombok.Getter;

@Getter
public enum KafkaTopic {

	PROMOTION_EVENT("promotion_event"),
	PROMOTION_RESULT("promotion_result");

	private final String topic;

	KafkaTopic(String topic) {
		this.topic = topic;
	}
}
