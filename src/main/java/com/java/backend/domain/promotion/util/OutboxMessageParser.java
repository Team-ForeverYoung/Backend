package com.java.backend.domain.promotion.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.OutboxBase;

@Component
public class OutboxMessageParser {

	private final ObjectMapper objectMapper;

	public OutboxMessageParser(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public OutboxBase outboxParser(String message) throws JsonProcessingException {
		return objectMapper.readValue(message, OutboxBase.class);
	}

	public EventJoinMessage eventJoinMessageParser(String message) throws JsonProcessingException {
		OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
		EventJoinMessage eventJoinMessage = objectMapper.readValue(outboxBase.getPayload(), EventJoinMessage.class);
		return eventJoinMessage;
	}
}
