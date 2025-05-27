package com.java.backend.domain.promotion.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.service.EventService;

@Component
public class PromotionEventConsumerKafka implements PromotionEventConsumer{
	private final EventService eventService;
	private static final String TOPIC = "promotion_event";
	private static final String SUMMER_EVENT = "SummerEvent";

	public PromotionEventConsumerKafka(EventService eventService) {
		this.eventService = eventService;
	}

	@Override
	@KafkaListener(topics = TOPIC)
	public void promotionEventJoinConsumer(EventJoinMessage message) {
		switch (message.getPromotionKey()){
			case SUMMER_EVENT:
				eventService.joinEvent(message.getEventJoinRequestDto());
				break;
			default:
				break;
		}
	}
}
