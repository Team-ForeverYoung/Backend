package com.java.backend.domain.promotion.dto;

import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.member.user.entity.User;

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

	public static EventResultMessage success(UserEvent userEvent, boolean success){
		return new EventResultMessage(success, userEvent.getId(), userEvent.getId(), userEvent.getUser().getName(), userEvent.getEvent().getEventName());
	}

	public static EventResultMessage fail(boolean fail, User user, Event event){
		return new EventResultMessage(fail, user.getId(), event.getId(), user.getName(), event.getEventName());
	}
}
