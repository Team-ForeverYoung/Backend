package com.java.backend.global.kafka;

import lombok.Getter;

@Getter
public enum KafkaTopic {

	PROMOTION_EVENT("promotion_event"),
	PROMOTION_RESULT("promotion_result"),
	USER_POINT("user_point");


	private final String topic;

	KafkaTopic(String topic) {
		this.topic = topic;
	}
}
