package com.java.backend.domain.promotion.dto;

import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
public class EventJoinRequestDto {
	// 클라이언트로 부터의 요청
	private  Long eventId;
	private  Long userId;


	public EventJoinRequestDto(Long eventId, Long userId) {
		this.eventId = eventId;
		this.userId = userId;
	}

	public EventJoinRequestDto() {
	}

	public Long getEventId() {
		return eventId;
	}

	public Long getUserId() {
		return userId;
	}
}
