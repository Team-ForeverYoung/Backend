package com.java.backend.global.kafka;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OutboxEvent {
	@Id @GeneratedValue
	private Long id;

	private String topic;

	@Column(name = "message_key")
	private String messageKey;

	@Lob
	private String payload;

	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	private OutBoxStatus status;

}
