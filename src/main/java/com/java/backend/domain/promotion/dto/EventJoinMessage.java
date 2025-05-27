package com.java.backend.domain.promotion.dto;

import lombok.Getter;

@Getter
public class EventJoinMessage {
	private final EventJoinRequestDto eventJoinRequestDto;
	private final String promotionKey;

	public EventJoinMessage(EventJoinRequestDto eventJoinRequestDto, String promotionKey) {
		this.eventJoinRequestDto = eventJoinRequestDto;
		this.promotionKey = promotionKey;
	}
}
