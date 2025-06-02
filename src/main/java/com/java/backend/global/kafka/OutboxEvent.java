package com.java.backend.global.kafka;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {
	@Id @GeneratedValue
	private Long id;

	private String topic;
	private String key;
	@Lob
	private String payload;
	private LocalDateTime createdAt;
	@Enumerated(EnumType.STRING)
	private OutBoxStatus status;    // READY, PUBLISHED, FAILED 등
}

