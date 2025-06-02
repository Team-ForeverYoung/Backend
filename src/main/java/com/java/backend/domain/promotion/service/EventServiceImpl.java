package com.java.backend.domain.promotion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.factory.EventFactory;
import com.java.backend.domain.promotion.message.PromotionEventProducer;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.repository.EventRepoService;
import com.java.backend.domain.promotion.service.join.EventJoinService;

import jakarta.transaction.Transactional;
@Service
public class EventServiceImpl implements EventService {
	private final EventRepoService eventRepoService;
	private final CouponRepoService couponRepoService;
	private final EventJoinService eventJoinService;
	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	public EventServiceImpl(EventRepoService eventRepoService,
		CouponRepoService couponRepoService,
		EventJoinService eventJoinService) {
		this.eventRepoService = eventRepoService;
		this.couponRepoService = couponRepoService;
		this.eventJoinService = eventJoinService;
	}

	@Override
	public Event createEvent(EventCreateRequestDto dto) {
		Coupon coupon = getCouponByCouponId(dto.getCouponId());
		EventCreateRequestDto eventCreateRequest = recreateDto(dto, coupon);
		Event event = EventFactory.createFromAdminRequest(eventCreateRequest);
		return eventRepoService.saveEvent(event);
	}

	@Override
	@Transactional
	public void joinEvent(EventJoinRequestDto dto) {
		eventJoinService.join(dto);
	}

	@Override
	@Transactional
	public void publishEventJoin(EventJoinRequestDto dto) {
		eventJoinService.publish(dto);
	}

	//---------------------- 유틸 -------------------------------

	private Coupon getCouponByCouponId(Long couponId) {
		return couponRepoService.getCoupon(couponId);
	}

	private EventCreateRequestDto recreateDto(EventCreateRequestDto dto, Coupon coupon) {
		return EventCreateRequestDto.of(dto, coupon);
	}



}
