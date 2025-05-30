package com.java.backend.domain.promotion.dto;

import com.java.backend.domain.promotion.entity.UserEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class EventResultMessage {
	private final boolean success;
	private final Long userId;
	private final Long eventId;
	private final String userName;
	private final String eventName;

	public EventResultMessage(boolean success, Long userId, Long eventId, String userName, String eventName) {
		this.success = success;
		this.userId = userId;
		this.eventId = eventId;
		this.userName = userName;
		this.eventName = eventName;
	}

	public EventResultMessage(UserEvent userEvent, boolean success, String userName, String eventName){
		this.success = success;
		this.userId = userEvent.getUser().getId();
		this.eventId = userEvent.getEvent().getId();
		this.userName = userName;
		this.eventName = eventName;
	}
}
