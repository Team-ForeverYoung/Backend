package com.java.backend.domain.promotion.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.backend.domain.promotion.code.PromotionCode;
import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.service.CouponService;
import com.java.backend.domain.promotion.service.EventService;
import com.java.backend.global.code.RestApiResponse;

@RestController
@RequestMapping("/api/v1/admin/event")
public class EventAdminController {
	private final EventService eventService;

	public EventAdminController(EventService eventService) {
		this.eventService = eventService;
	}

	@PostMapping
	public ResponseEntity<RestApiResponse> createEvent(@RequestBody EventCreateRequestDto dto){
		eventService.createEvent(dto);
		RestApiResponse restApiResponse = new RestApiResponse(PromotionCode.EVENT_CREATED_SUCCESS);
		return ResponseEntity.ok(restApiResponse);
	}
}
