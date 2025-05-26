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
			System.out.println("프로듀서 진입");
			String value = objectMapper.writeValueAsString(eventJoinMessage.getEventJoinRequestDto());
			System.out.println("직렬화 성공" + value);
			String key = String.valueOf(eventJoinMessage.getEventJoinRequestDto().getUserId());
			System.out.println("키 조회" + key);
			kafkaProducerUtil.send(eventJoinMessage.getTopic(), key, value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("카프카 메시지 직렬화 오류", e);
		}
	}

}
