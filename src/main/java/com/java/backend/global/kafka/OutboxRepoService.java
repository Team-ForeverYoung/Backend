package com.java.backend.global.kafka;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OutboxRepoService {
	private final OutboxRepository outboxRepository;
	public OutboxRepoService(OutboxRepository outboxRepository) {
		this.outboxRepository = outboxRepository;
	}

	public void saveEvent(OutboxEvent event) {
		outboxRepository.save(event);
	}

}
