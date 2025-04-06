package com.java.backend.domain.promotion.service;

import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;

public interface EventService {
	public void createEvent(EventCreateRequestDto dto);
	public void joinEvent(EventJoinRequestDto dto);
}
