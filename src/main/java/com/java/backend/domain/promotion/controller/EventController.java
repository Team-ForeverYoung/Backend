package com.java.backend.domain.promotion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.backend.domain.promotion.code.PromotionCode;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.service.EventService;
import com.java.backend.global.code.ResponseApiCode;
import com.java.backend.global.code.RestApiResponse;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
	private final EventService eventService;
	private static final Logger log = LoggerFactory.getLogger(EventController.class);

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping
	public ResponseEntity<ResponseApiCode> join(@RequestBody EventJoinRequestDto dto){

		log.debug("EventJoinRequestDto: {}", dto);
		log.debug("eventId: {}", dto.getEventId());
		log.debug("userId: {}", dto.getUserId());
		eventService.publishEventJoin(dto);
		RestApiResponse restApiResponse = new RestApiResponse(PromotionCode.USER_EVENT_CREATED_SUCCESS);
		return ResponseEntity.ok(restApiResponse);
	}
}
