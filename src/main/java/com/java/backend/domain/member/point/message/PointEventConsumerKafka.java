package com.java.backend.domain.member.point.message;

import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.UpdatePointReqMessage;
import com.java.backend.domain.member.point.service.PointService;
import com.java.backend.domain.promotion.dto.OutboxBase;

public class PointEventConsumerKafka {
	private static final String TOPIC = "user_point";
	private final PointService pointService;
	public PointEventConsumerKafka(PointService pointService, ObjectMapper objectMapper) {
		this.pointService = pointService;
		this.objectMapper = objectMapper;
	}

	private final ObjectMapper objectMapper;
	@KafkaListener(topics = TOPIC, containerFactory = "outboxMessageListenerContainerFactory")
	public void saveUserPointConsumer(String message){
		try {
			OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
			UpdatePointReqMessage savePointMessage = objectMapper.readValue(outboxBase.getPayload(), UpdatePointReqMessage.class);
			pointService.updateUserPoint(savePointMessage);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
