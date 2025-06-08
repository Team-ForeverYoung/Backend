package com.java.backend.domain.promotion.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.dto.OutboxBase;
import com.java.backend.domain.promotion.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.util.OutboxMessageParser;

@Component
public class PromotionEventConsumerKafka implements PromotionEventConsumer {
	private final EventService eventService;
	private final OutboxMessageParser outboxMessageParser;
	private static final String TOPIC = "forever_mysql_db.forever_mysql_db.outbox_event";
	private static final String SUMMER_EVENT = "SummerEvent";
	private static final Logger log = LoggerFactory.getLogger(PromotionEventConsumerKafka.class);
	public PromotionEventConsumerKafka(EventService eventService, OutboxMessageParser outboxMessageParser) {
		this.eventService = eventService;
		this.outboxMessageParser = outboxMessageParser;
	}

	@Override
	@KafkaListener(topics = TOPIC)
	public void promotionEventJoinConsumer(String message) {
		try {
			log.info("Message Receive");
			log.info(message);
			OutboxBase outbox = outboxMessageParser.outboxParser(message);
			EventJoinMessage eventJoinMessage = outboxMessageParser.eventJoinMessageParser(message);
			switch (outbox.getMessageKey()) {
				case SUMMER_EVENT:
					eventService.joinEvent(eventJoinMessage.getEventJoinRequestDto());
					break;
				default:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
