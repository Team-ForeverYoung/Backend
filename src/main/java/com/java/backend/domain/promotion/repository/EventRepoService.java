package com.java.backend.domain.promotion.repository;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.entity.Event;

@Service
public class EventRepoService {
	private final EventRepository eventRepository;

	public EventRepoService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public void saveEvent(Event event){
		eventRepository.save(event);
	}
	//ToDo: 예외처리
	public Event findEventByEventId(Long eventId) {
		return eventRepository.findById(eventId)
			.orElseThrow();
	}
}
