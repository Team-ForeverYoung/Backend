package com.java.backend.promotion.repository;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.exception.EventException;
import com.java.backend.domain.promotion.repository.EventRepoService;
import com.java.backend.domain.promotion.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
class EventRepoTest {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventRepoService eventRepoService;

	@Test
	void 이벤트_저장_테스트() {
		// given
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

		// mocking
		when(eventRepository.save(event)).thenReturn(event);

		// when
		Event event1 = eventRepoService.saveEvent(event);
		// then
		Assertions.assertEquals(event1.getEventName(),event.getEventName());
		Assertions.assertEquals(event1.getId(),event.getId());
		Assertions.assertEquals(event1.getAmount(),event.getAmount());
		Assertions.assertEquals(event1.getExpiredTime(),event.getExpiredTime());
		Assertions.assertEquals(event1.getCoupon(),event.getCoupon());
	}

	@Test
	void 이벤트_조회_테스트(){
		// given
		Long eventId = 1L;
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

		// mocking
		when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

		// when
		Event foundEvent = eventRepoService.findEventByEventId(eventId);

		// then
		Assertions.assertNotNull(foundEvent); // 반환된 이벤트가 null이 아닌지 확인
		Assertions.assertEquals(event.getEventName(), foundEvent.getEventName()); // 이벤트 이름이 일치하는지 확인
		Assertions.assertEquals(event.getCoupon(), foundEvent.getCoupon()); // 쿠폰이 일치하는지 확인
	}
	@Test
	void 이벤트_조회_예외_테스트(){
		// given
		Long eventId = 1L;

		// mocking
		when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

		// when & then
		Assertions.assertThrows(EventException.eventNotFoundException.class, () -> {
			eventRepoService.findEventByEventId(eventId);
		});
	}

}
