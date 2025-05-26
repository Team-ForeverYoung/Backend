package com.java.backend.domain.promotion.dto;

import lombok.Getter;

@Getter
public class EventJoinMessage {
	private final EventJoinRequestDto eventJoinRequestDto;
	private final String topic;

	public EventJoinMessage(EventJoinRequestDto eventJoinRequestDto, String topic) {
		this.eventJoinRequestDto = eventJoinRequestDto;
		this.topic = topic;
	}
}
