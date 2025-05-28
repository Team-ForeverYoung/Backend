package com.java.backend.domain.promotion.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.dto.EventResultMessage;
import com.java.backend.domain.promotion.service.EventServiceImpl;
import com.java.backend.global.kafka.KafkaProducerUtil;

@Component
public class PromotionEventProducerKafka implements PromotionEventProducer{
	//토픽
	private static final String TOPIC = "promotion_event";
	private static final String TOPIC2= "promotion_result";
	private final KafkaProducerUtil kafkaProducerUtil;
	private static final Logger log = LoggerFactory.getLogger(PromotionEventProducerKafka.class);
	public PromotionEventProducerKafka(KafkaProducerUtil kafkaProducerUtil) {
		this.kafkaProducerUtil = kafkaProducerUtil;
	}



	@Override
	public void promotionEventJoinProducer(EventJoinMessage eventJoinMessage) {
			String key = eventJoinMessage.getPromotionKey();
			EventJoinMessage value = eventJoinMessage;
			kafkaProducerUtil.sendEventJoinMessage(TOPIC, key, value);

	}

	@Override
	public void promotionResultProducer(EventResultMessage eventResultMessage) {
		String key = String.valueOf(eventResultMessage.getUserId());
		EventResultMessage value = eventResultMessage;
		kafkaProducerUtil.sendEventResultMessage(TOPIC2, key, value);
	}

}
