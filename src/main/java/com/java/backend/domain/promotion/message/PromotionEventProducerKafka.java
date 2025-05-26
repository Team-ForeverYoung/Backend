package com.java.backend.domain.promotion.message;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.global.kafka.KafkaProducerUtil;

@Component
public class PromotionEventProducerKafka implements PromotionEventProducer{

	private final KafkaProducerUtil kafkaProducerUtil;
	private ObjectMapper objectMapper;
	public PromotionEventProducerKafka(KafkaProducerUtil kafkaProducerUtil) {
		this.kafkaProducerUtil = kafkaProducerUtil;
	}



	@Override
	public void promotionEventJoinProducer(EventJoinMessage eventJoinMessage) {
		try {
			String value = objectMapper.writeValueAsString(eventJoinMessage.getEventJoinRequestDto());
			String key = String.valueOf(eventJoinMessage.getEventJoinRequestDto().getUserId());
			kafkaProducerUtil.send(eventJoinMessage.getTopic(), key, value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("카프카 메시지 직렬화 오류", e);
		}
	}

}
