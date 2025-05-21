package com.java.backend.domain.promotion.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.dto.UserEventFactoryInput;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.promotion.factory.EventFactory;
import com.java.backend.domain.promotion.factory.UserEventFactory;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.repository.EventRepoService;
import com.java.backend.domain.promotion.repository.UserEventRepoService;
import com.java.backend.domain.promotion.stub.StubUserRepository;
import com.java.backend.domain.user.entity.User;

import jakarta.transaction.Transactional;

@Service
public class EventServiceImpl implements EventService{
	private final EventRepoService eventRepoService;
	private final CouponRepoService couponRepoService;
	private final StubUserRepository stubUserRepository;
	private final UserEventRepoService userEventRepoService;

	public EventServiceImpl(EventRepoService eventRepoService, CouponRepoService couponRepoService,
		StubUserRepository stubUserRepository, UserEventRepoService userEventRepoService) {
		this.eventRepoService = eventRepoService;
		this.couponRepoService = couponRepoService;
		this.stubUserRepository = stubUserRepository;
		this.userEventRepoService = userEventRepoService;
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
	public UserEvent joinEvent(EventJoinRequestDto dto) {
		Event event = getEventByEventId(dto.getEventId());
		Coupon coupon =event.getCoupon();
		User user = getUserByUserId(dto.getUserId());
		UserEventFactoryInput dtto = new UserEventFactoryInput(coupon,event,user);
		UserEvent userEvent = UserEventFactory.createFromUserRequest(dtto);
		if (event.getAmount() <= 0) {
			throw new IllegalStateException("이벤트 수량이 부족합니다.");
		}
		event.subtractAmount(); // 더티체킹
		return userEventRepoService.saveUserEvent(userEvent);
	}
	//ToDo: 임시 이므로 추후 갈아끼워줘야 함
	private User getUserByUserId(Long userId){
		return stubUserRepository.findById(userId).orElseThrow();
	}

	private Event getEventByEventId(Long eventId){
		return eventRepoService.findEventByEventIdForUpdate(eventId);
	}
	private Coupon getCouponByCouponId(Long couponId){
		return couponRepoService.getCoupon(couponId);
	}

	private EventCreateRequestDto recreateDto(EventCreateRequestDto dto, Coupon coupon){
		return EventCreateRequestDto.of(dto, coupon);
	}

}
