package com.java.backend.domain.promotion.dto;

import com.java.backend.global.kafka.OutBoxStatus;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OutboxBase {
	private Long id;
	@Column(name = "created_at")
	private Long createdAt;
	@Column(name = "message_key")
	private String messageKey;
	private String payload;
	private OutBoxStatus status;

	@Override
	public String toString() {
		return "OutboxBase{" +
			"id=" + id +
			", createdAt=" + createdAt +
			", messageKey='" + messageKey + '\'' +
			", payload='" + payload + '\'' +
			", status=" + status +
			", topic='" + topic + '\'' +
			'}';
	}

	private String topic;
}
