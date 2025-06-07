package com.java.backend.domain.promotion.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PromotionEventConsumerKafka implements PromotionEventConsumer {
	private final EventService eventService;
	private static final String TOPIC = "forever_mysql_db.forever_mysql_db.outbox_event";
	private static final String SUMMER_EVENT = "SummerEvent";
	private final ObjectMapper objectMapper = new ObjectMapper();

	public PromotionEventConsumerKafka(EventService eventService) {
		this.eventService = eventService;
	}

	@Override
	@KafkaListener(topics = TOPIC,
		containerFactory = "kafkaListenerContainerFactory")
	public void promotionEventJoinConsumer(EventJoinMessage message) {
		try {
			// EventJoinMessage eventJoinMessage = objectMapper.readValue(message, EventJoinMessage.class);
			switch (message.getPromotionKey()) {
				case SUMMER_EVENT:
					// eventService.joinEvent(eventJoinMessage.getEventJoinRequestDto());
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
