package com.java.backend.domain.promotion.dto;

import com.java.backend.domain.promotion.entity.UserEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class EventResultMessage {
	private final boolean success;
	private final Long userId;
	private final Long eventId;

	public EventResultMessage(boolean success, Long userId, Long eventId) {
		this.success = success;
		this.userId = userId;
		this.eventId = eventId;
	}

	public EventResultMessage(UserEvent userEvent, boolean success){
		this.success = success;
		this.userId = userEvent.getUser().getId();
		this.eventId = userEvent.getEvent().getId();
	}
}
