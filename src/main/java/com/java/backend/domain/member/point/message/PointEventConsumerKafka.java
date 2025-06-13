package com.java.backend.domain.member.point.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.UpdatePointReqMessage;
import com.java.backend.domain.member.point.service.PointService;
import com.java.backend.domain.promotion.dto.OutboxBase;

public class PointEventConsumerKafka {

	private static final Logger log = LoggerFactory.getLogger(PointEventConsumerKafka.class);
	private static final String TOPIC = "user_point";

	private final PointService pointService;
	private final ObjectMapper objectMapper;

	public PointEventConsumerKafka(PointService pointService, ObjectMapper objectMapper) {
		this.pointService = pointService;
		this.objectMapper = objectMapper;
	}

	@KafkaListener(topics = TOPIC, containerFactory = "outboxMessageListenerContainerFactory")
	public void saveUserPointConsumer(String message) {
		log.info("[Kafka] Received message on topic '{}': {}", TOPIC, message);
		log.info("해치웠나ㅓ ?");
		try {
			OutboxBase outboxBase = objectMapper.readValue(message, OutboxBase.class);
			log.info("[Kafka] Parsed OutboxBase: {}", outboxBase);

			UpdatePointReqMessage savePointMessage = objectMapper.readValue(outboxBase.getPayload(), UpdatePointReqMessage.class);
			log.info("[Kafka] Parsed UpdatePointReqMessage: {}", savePointMessage);

			pointService.updateUserPoint(savePointMessage);
			log.info("[Kafka] Successfully updated user point.");

		} catch (JsonProcessingException e) {
			log.info("[Kafka] JSON processing failed for message: {}", message, e);
		} catch (Exception e) {
			log.info("[Kafka] Unexpected error while processing message: {}", message, e);
		}
	}
}
