package com.java.backend.domain.promotion.dto;

import lombok.Getter;

@Getter
public class EventResultMessage {
	private boolean success;
	private Long userId;
	private Long eventId;

}
