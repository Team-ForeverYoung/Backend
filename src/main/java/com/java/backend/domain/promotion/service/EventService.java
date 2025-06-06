package com.java.backend.domain.promotion.service;

import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;

public interface EventService {
	public Event createEvent(EventCreateRequestDto dto);
	public void joinEvent(EventJoinRequestDto dto);
	public void publishEventJoin(EventJoinRequestDto dto);

}
