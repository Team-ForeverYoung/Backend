package com.java.backend.domain.promotion.dto;

import lombok.Getter;

@Getter
public class EventJoinMessage {
	private  EventJoinRequestDto eventJoinRequestDto;
	private  String promotionKey;

	public EventJoinMessage() {
	}
	public EventJoinMessage(EventJoinRequestDto eventJoinRequestDto, String promotionKey) {
		this.eventJoinRequestDto = eventJoinRequestDto;
		this.promotionKey = promotionKey;
	}
}
