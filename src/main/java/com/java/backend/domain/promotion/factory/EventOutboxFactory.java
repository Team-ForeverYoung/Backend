package com.java.backend.domain.promotion.factory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.dto.EventResultMessage;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.promotion.message.KafkaTopic;
import com.java.backend.domain.user.entity.User;
import com.java.backend.global.kafka.OutBoxStatus;
import com.java.backend.global.kafka.OutboxEvent;
@Component
public class EventOutboxFactory {
	private final ObjectMapper objectMapper;

	public EventOutboxFactory(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public OutboxEvent createEventResultSuccessOutbox(UserEvent userEvent, boolean success){
		String key = userEvent.getEvent().getEventName();
		EventResultMessage eventResultMessage = EventResultMessage.success(userEvent, success);
		String value = convertValueToString(eventResultMessage);
		return createOutbox(key, value, KafkaTopic.PROMOTION_RESULT, OutBoxStatus.READY);
	}

	public OutboxEvent createEventResultFailOutbox(Event event, User user, boolean fail){
		String key = event.getEventName();
		EventResultMessage eventResultMessage = EventResultMessage.fail(fail, user, event);
		String value = convertValueToString(eventResultMessage);
		return createOutbox(key, value, KafkaTopic.PROMOTION_RESULT, OutBoxStatus.READY);
	}

	public OutboxEvent createEventJoinOutbox(EventJoinMessage message){
		String key = message.getPromotionKey();
		String value = convertValueToString(message);
		return createOutbox(key, value, KafkaTopic.PROMOTION_EVENT,OutBoxStatus.READY);
	}

	private OutboxEvent createOutbox(String key, String value, KafkaTopic topic, OutBoxStatus status){
		return OutboxEvent.builder().topic(topic.getTopic()).key(key).payload(value).status(status).createdAt(
			LocalDateTime.now()).build();
	}

	private String convertValueToString(Object value){
		try{
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
