package com.java.backend.promotion.service;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.dto.EventJoinRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.promotion.exception.EventException;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.repository.EventRepoService;
import com.java.backend.domain.promotion.repository.UserEventRepoService;
import com.java.backend.domain.promotion.service.EventServiceImpl;
import com.java.backend.domain.promotion.stub.StubUserRepository;
import com.java.backend.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
	@Mock
	private EventRepoService eventRepoService;
	@Mock
	private CouponRepoService couponRepoService;

	@Mock
	private StubUserRepository stubUserRepository;

	@Mock
	private UserEventRepoService userEventRepoService;

	@InjectMocks
	private EventServiceImpl eventService;



	@Test
	void 이벤트_생성_서비스_테스트(){
		//given
		String couponName = "취업하기 쿠폰";
		LocalDate couponExpiredTime = LocalDate.of(2025, 12, 31);
		String couponBenefit = "취준시 취업 확률 500% 증가!";

		Coupon coupon = Coupon.builder()
			.name(couponName)
			.expiredTime(couponExpiredTime)
			.benefit(couponBenefit)
			.build();

		String eventName = "취준생이여 올영으로 모여라 !!";
		LocalDate eventExpiredTime = LocalDate.of(2025, 8, 31);
		int eventAmount = 3;

		Event expectedEvent = Event.builder()
			.eventName(eventName)
			.expiredTime(eventExpiredTime)
			.amount(eventAmount)
			.coupon(coupon)
			.build();

		EventCreateRequestDto dto = new EventCreateRequestDto(eventName, eventExpiredTime, eventAmount, 1L);

		//mocking
		when(couponRepoService.getCoupon(1L)).thenReturn(coupon);
		when(eventRepoService.saveEvent(any(Event.class))).thenReturn(expectedEvent);

		// when
		Event createdEvent = eventService.createEvent(dto);

		// then
		Assertions.assertEquals(expectedEvent.getCoupon(), createdEvent.getCoupon());
	}

	@Test
	void 이벤트_참여_성공_테스트(){
		//given
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();

		Event event = Event.builder()
			.eventName("취준생이여 올영으로 모여라 !!")
			.expiredTime(LocalDate.of(2025, 8, 31))
			.amount(3)
			.coupon(coupon)
			.build();
		User user = User.builder()
			.name("김우성")
			.country("KR")
			.email("abuga991008@gmail.com")
			.passWord("123123123")
			.point(1000000)
			.build();
		UserEvent expectedUserEvent = UserEvent.builder()
			.event(event)
			.coupon(coupon)
			.user(user)
			.build();

		EventJoinRequestDto dto = new EventJoinRequestDto(1L,1L);
		//mocking
		when(stubUserRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
		when(eventRepoService.findEventByEventId(1L)).thenReturn(event);
		when(userEventRepoService.saveUserEvent(any(UserEvent.class))).thenReturn(expectedUserEvent);

		//when
		UserEvent userEvent = eventService.joinEvent(dto);

		//then
		Assertions.assertEquals(expectedUserEvent.getEvent().getEventName(), userEvent.getEvent().getEventName());
		Assertions.assertEquals(expectedUserEvent.getCoupon().getName(), userEvent.getCoupon().getName());
		Assertions.assertEquals(expectedUserEvent.getUser().getName(), userEvent.getUser().getName());

	}

	@Test
	void 수량만료로인한_이벤트_실패_테스트(){
		//given
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();

		Event event = Event.builder()
			.eventName("취준생이여 올영으로 모여라 !!")
			.expiredTime(LocalDate.of(2025, 8, 31))
			.amount(1)
			.coupon(coupon)
			.build();
		User user = User.builder()
			.name("김우성")
			.country("KR")
			.email("abuga991008@gmail.com")
			.passWord("123123123")
			.point(1000000)
			.build();
		UserEvent expectedUserEvent = UserEvent.builder()
			.event(event)
			.coupon(coupon)
			.user(user)
			.build();

		EventJoinRequestDto dto = new EventJoinRequestDto(1L,1L);
		//mocking
		when(stubUserRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
		when(eventRepoService.findEventByEventId(1L)).thenReturn(event);
		when(userEventRepoService.saveUserEvent(any(UserEvent.class))).thenReturn(expectedUserEvent);

		//when
		UserEvent userEvent = eventService.joinEvent(dto);

		//when && then
		Assertions.assertThrows(EventException.eventSoledOutException.class, () -> {
			eventService.joinEvent(dto);
		});

	}
}
