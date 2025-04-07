package com.java.backend.domain.promotion.repository;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.code.PromotionErrorCode;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.exception.EventException;
import com.java.backend.global.exception.ExceptionMetaData;

@Service
public class EventRepoService {
	private final EventRepository eventRepository;

	public EventRepoService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public Event saveEvent(Event event){
		return eventRepository.save(event);
	}
	//ToDo: 예외처리
	public Event findEventByEventId(Long eventId) {
		return eventRepository.findById(eventId)
			.orElseThrow(() ->
			new EventException.eventNotFoundException(
				ExceptionMetaData.builder()
					.responseApiCode(PromotionErrorCode.EVENT_NOT_FOUND)
					.className(this.getClass().getName())
					.build()
			)
		);
	}

}
