package com.java.backend.domain.promotion.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.service.EventServiceImpl;
import com.java.backend.global.kafka.KafkaProducerUtil;

@Component
public class PromotionEventProducerKafka implements PromotionEventProducer{
	//토픽
	private static final String TOPIC = "promotion_event";
	private final KafkaProducerUtil kafkaProducerUtil;
	private final ObjectMapper objectMapper;
	private static final Logger log = LoggerFactory.getLogger(PromotionEventProducerKafka.class);
	public PromotionEventProducerKafka(KafkaProducerUtil kafkaProducerUtil, ObjectMapper objectMapper) {
		this.kafkaProducerUtil = kafkaProducerUtil;
		this.objectMapper = objectMapper;
	}



	@Override
	public void promotionEventJoinProducer(EventJoinMessage eventJoinMessage) {
		try {
			String value = objectMapper.writeValueAsString(eventJoinMessage.getEventJoinRequestDto());
			String key = eventJoinMessage.getPromotionKey();

			kafkaProducerUtil.send(TOPIC, key, value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("카프카 메시지 직렬화 오류", e);
		}
	}

}
