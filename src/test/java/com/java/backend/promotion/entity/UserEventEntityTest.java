package com.java.backend.promotion.entity;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.entity.UserEvent;
import com.java.backend.domain.member.user.entity.User;

public class UserEventEntityTest {
	@Test
	void 유저_이벤트_생성_테스트(){
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
		//when
		UserEvent userEvent = UserEvent.builder()
			.event(event)
			.coupon(coupon)
			.user(user)
			.build();
		//when
		Assertions.assertEquals(userEvent.getEvent(), event);
		Assertions.assertEquals(userEvent.getCoupon(), coupon);
		Assertions.assertEquals(userEvent.getUser(),user);

	}
}
