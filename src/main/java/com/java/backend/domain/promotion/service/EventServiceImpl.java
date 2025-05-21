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
public class EventServiceImpl implements EventService {
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
		validateDuplicateJoin(dto.getUserId(), dto.getEventId());
		Event event = getEventByEventId(dto.getEventId());
		validateEventAmount(event);

		Coupon coupon = event.getCoupon();
		User user = getUserByUserId(dto.getUserId());
		UserEventFactoryInput dtto = new UserEventFactoryInput(coupon, event, user);
		UserEvent userEvent = UserEventFactory.createFromUserRequest(dtto);

		event.subtractAmount(); // 더티체킹
		return userEventRepoService.saveUserEvent(userEvent);
	}

	@Override
	public boolean isJoined(Long userId, Long eventId) {
		// 구현 필요
		return false;
	}

	// ==== 유효성 검사 메서드들 ====
	private void validateDuplicateJoin(Long userId, Long eventId) {
		boolean isExist = userEventRepoService.isExistUserEvent(userId, eventId);
		if (isExist) {
			throw new IllegalStateException("중복참여 입니다.");
		}
	}

	private void validateEventAmount(Event event) {
		if (event.getAmount() <= 0) {
			throw new IllegalStateException("이벤트 수량이 부족합니다.");
		}
	}


	private User getUserByUserId(Long userId) {
		return stubUserRepository.findById(userId).orElseThrow();
	}

	private Event getEventByEventId(Long eventId) {
		return eventRepoService.findEventByEventIdForUpdate(eventId);
	}

	private Coupon getCouponByCouponId(Long couponId) {
		return couponRepoService.getCoupon(couponId);
	}

	private EventCreateRequestDto recreateDto(EventCreateRequestDto dto, Coupon coupon) {
		return EventCreateRequestDto.of(dto, coupon);
	}
}
