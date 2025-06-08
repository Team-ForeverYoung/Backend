package com.java.backend.domain.promotion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventJoinMessage {
	private  EventJoinRequestDto eventJoinRequestDto;
	private  String promotionKey;

	@Override
	public String toString() {
		return "EventJoinMessage{" +
			"eventJoinRequestDto=" + eventJoinRequestDto +
			", promotionKey='" + promotionKey + '\'' +
			'}';
	}

	public EventJoinMessage() {
	}
	public EventJoinMessage(EventJoinRequestDto eventJoinRequestDto, String promotionKey) {
		this.eventJoinRequestDto = eventJoinRequestDto;
		this.promotionKey = promotionKey;
	}
}
