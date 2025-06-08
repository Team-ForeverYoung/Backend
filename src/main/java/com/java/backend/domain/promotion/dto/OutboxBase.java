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
	private Long created_at;
	@Column(name = "message_key")
	private String message_key;
	private String payload;
	private OutBoxStatus status;
	private String topic;
	@Override
	public String toString() {
		return "OutboxBase{" +
			"id=" + id +
			", created_at=" + created_at +
			", message_key='" + message_key + '\'' +
			", payload='" + payload + '\'' +
			", status=" + status +
			", topic='" + topic + '\'' +
			'}';
	}


}
