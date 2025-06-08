package com.java.backend.domain.promotion.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.OutboxBase;

@Component
public class OutboxMessageParser {

	private final ObjectMapper objectMapper;
	private static final Logger log = LoggerFactory.getLogger(OutboxMessageParser.class);
	public OutboxMessageParser(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public OutboxBase outboxParser(String message) throws JsonProcessingException {
		OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
		log.info(outboxBase.toString());
		return outboxBase;
	}

	public EventJoinMessage eventJoinMessageParser(String message) throws JsonProcessingException {
		OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
		EventJoinMessage eventJoinMessage = objectMapper.readValue(outboxBase.getPayload(), EventJoinMessage.class);
		log.info(eventJoinMessage.toString());
		return eventJoinMessage;
	}
}
