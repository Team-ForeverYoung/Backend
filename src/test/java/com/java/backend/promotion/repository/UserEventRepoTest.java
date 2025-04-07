package com.java.backend.promotion.repository;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.promotion.repository.UserEventRepoService;
import com.java.backend.domain.promotion.repository.UserEventRepository;
import com.java.backend.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserEventRepoTest {

	@Mock
	private UserEventRepository userEventRepository;
	@InjectMocks
	private UserEventRepoService userEventRepoService;

	@Test
	void 유저_이벤트_레포_저장_테스트(){
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
		UserEvent userEvent = UserEvent.builder()
			.event(event)
			.coupon(coupon)
			.user(user)
			.build();

		//mocking
		when(userEventRepository.save(userEvent)).thenReturn(userEvent);

		//when
		UserEvent savedUserEvent = userEventRepoService.saveUserEvent(userEvent);

		//when
		Assertions.assertEquals(userEvent.getCoupon(),savedUserEvent.getCoupon());
		Assertions.assertEquals(userEvent.getEvent(),savedUserEvent.getEvent());
		Assertions.assertEquals(userEvent.getUser(),savedUserEvent.getUser());
		Assertions.assertEquals(userEvent.getId(),savedUserEvent.getId());
	}
}
