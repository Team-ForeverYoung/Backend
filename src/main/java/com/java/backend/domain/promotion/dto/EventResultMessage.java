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

	private EventResultMessage(boolean success, Long userId, Long eventId, String userName, String eventName) {
		this.success = success;
		this.userId = userId;
		this.eventId = eventId;
		this.userName = userName;
		this.eventName = eventName;
	}

	public static EventResultMessage success(UserEvent userEvent, boolean success, String userName, String eventName){
		return new EventResultMessage(success, userEvent.getId(), userEvent.getId(), userName, eventName);
	}

	public static EventResultMessage fail(boolean fail, Long userId,String userName, Long eventId, String eventName){
		return new EventResultMessage(fail, userId, eventId, userName, eventName);
	}
}
