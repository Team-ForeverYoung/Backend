package com.java.backend.domain.promotion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.controller.EventController;
import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.dto.UserEventFactoryInput;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.promotion.factory.EventFactory;
import com.java.backend.domain.promotion.factory.UserEventFactory;
import com.java.backend.domain.promotion.message.PromotionEventProducer;
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
	private final PromotionEventProducer promotionEventProducer;
	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	public EventServiceImpl(EventRepoService eventRepoService, CouponRepoService couponRepoService,
		StubUserRepository stubUserRepository, UserEventRepoService userEventRepoService,
		PromotionEventProducer promotionEventProducer) {
		this.eventRepoService = eventRepoService;
		this.couponRepoService = couponRepoService;
		this.stubUserRepository = stubUserRepository;
		this.userEventRepoService = userEventRepoService;
		this.promotionEventProducer = promotionEventProducer;
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

	@Override
	public void publishEventJoin(EventJoinRequestDto dto) {
		log.debug("서비스 진입");
		String topic = eventRepoService.findEventByEventId(dto.getEventId()).getEventName();
		log.debug("topic 조회 실행" + topic);
		EventJoinMessage eventJoinMessage = new EventJoinMessage(dto,topic);
		promotionEventProducer.promotionEventJoinProducer(eventJoinMessage);
	}

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
		// return  eventRepoService.findEventByEventId(eventId);
		return eventRepoService.findEventByEventIdForUpdate(eventId);
	}

	private Coupon getCouponByCouponId(Long couponId) {
		return couponRepoService.getCoupon(couponId);
	}

	private EventCreateRequestDto recreateDto(EventCreateRequestDto dto, Coupon coupon) {
		return EventCreateRequestDto.of(dto, coupon);
	}
}
