package com.java.backend.domain.promotion.service.join;

import org.springframework.stereotype.Component;

import com.java.backend.domain.member.user.repository.OliveUserRepository;
import com.java.backend.domain.promotion.code.PromotionErrorCode;
import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.dto.UserEventFactoryInput;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.promotion.exception.UserEventException;
import com.java.backend.domain.promotion.factory.UserEventFactory;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.repository.EventRepoService;
import com.java.backend.domain.promotion.repository.UserEventRepoService;
import com.java.backend.domain.promotion.factory.EventOutboxFactory;
import com.java.backend.domain.promotion.stub.StubUserRepository;
import com.java.backend.domain.member.user.entity.User;
import com.java.backend.global.exception.ExceptionMetaData;
import com.java.backend.global.kafka.OutboxRepoService;

@Component
public class EventJoinService {
	private final EventRepoService eventRepoService;
	private final CouponRepoService couponRepoService;
	private final OliveUserRepository oliveUserRepository;
	private final UserEventRepoService userEventRepoService;
	private final EventOutboxFactory eventOutboxFactory;
	private final OutboxRepoService outboxRepoService;
	public EventJoinService(EventRepoService eventRepoService, CouponRepoService couponRepoService, OliveUserRepository oliveUserRepository, UserEventRepoService userEventRepoService,
		EventOutboxFactory eventOutboxFactory, OutboxRepoService outboxRepoService) {
		this.eventRepoService = eventRepoService;
		this.couponRepoService = couponRepoService;
		this.oliveUserRepository = oliveUserRepository;
		this.userEventRepoService = userEventRepoService;
		this.eventOutboxFactory = eventOutboxFactory;
		this.outboxRepoService = outboxRepoService;
	}

	public void join(EventJoinRequestDto dto){
		try {
			validateDuplicateJoin(dto.getUserId(), dto.getEventId());
			Event event = getEventByEventId(dto.getEventId());
			User user = getUserByUserId(dto.getUserId());
			validateEventAmount(event);
			Coupon coupon = event.getCoupon();
			UserEvent userEvent = UserEventFactory.createFromUserRequest(new UserEventFactoryInput(coupon, event, user));
			event.subtractAmount();
			saveUserEvent(userEvent);
			outboxRepoService.saveEvent(eventOutboxFactory.createEventResultSuccessOutbox(userEvent, true));
		}catch (Exception e) {
			User user = getUserByUserId(dto.getUserId());
			Event event = getEventByEventId(dto.getEventId());
			outboxRepoService.saveEvent(eventOutboxFactory.createEventResultFailOutbox(event, user, false));
		}
	}

	public void publish(EventJoinRequestDto dto){
		Long eventId = dto.getEventId();
		String promotionKey = eventRepoService.findEventByEventId(eventId).getEventName();
		EventJoinMessage eventJoinMessage = new EventJoinMessage(dto,promotionKey);
		outboxRepoService.saveEvent(eventOutboxFactory.createEventJoinOutbox(eventJoinMessage));
	}


	//--------------------------- 유틸 ----------------------------
	private User getUserByUserId(Long userId) {
		return oliveUserRepository.findByUserPk(userId).orElseThrow();
	}

	private Event getEventByEventId(Long eventId) {
		// return  eventRepoService.findEventByEventId(eventId);
		return eventRepoService.findEventByEventIdForUpdate(eventId);
	}

	private Coupon getCouponByCouponId(Long couponId) {
		return couponRepoService.getCoupon(couponId);
	}

	private void saveUserEvent(UserEvent userEvent){
		userEventRepoService.saveUserEvent(userEvent);
	}

	//--------------------------- 검증 ------------------------------
	private void validateDuplicateJoin(Long userId, Long eventId) {
		boolean isExist = userEventRepoService.isExistUserEvent(userId, eventId);
		if (isExist) {
			ExceptionMetaData exceptionMetaData = ExceptionMetaData.builder()
				.className(null)
				.stackTrace(null)
				.responseApiCode(PromotionErrorCode.EVENT_DUPLICATED_JOIN).build();
			throw new UserEventException.UserEventDuplicatedException(exceptionMetaData);
		}
	}

	private void validateEventAmount(Event event) {
		if (event.getAmount() <= 0) {
			ExceptionMetaData exceptionMetaData = ExceptionMetaData.builder()
				.className(null)
				.stackTrace(null)
				.responseApiCode(PromotionErrorCode.THIS_EVENT_IS_SOLD_OUT).build();
			throw new UserEventException.UserEventSoldedOut(exceptionMetaData);
		}
	}
}
