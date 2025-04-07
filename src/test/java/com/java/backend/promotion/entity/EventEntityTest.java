package com.java.backend.promotion.entity;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.java.backend.domain.promotion.code.PromotionErrorCode;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.promotion.exception.EventException;

public class EventEntityTest {
	@Test
	void 이벤트_생성시_기본값_확인() {
		// given
		String eventName = "취준생이여 올영으로 모여라 !!";
		LocalDate expiredDate = LocalDate.of(2025, 8, 31);
		int amount = 3;
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();

		// when
		Event event = Event.builder()
			.eventName(eventName)
			.expiredTime(expiredDate)
			.amount(amount)
			.coupon(coupon)
			.build();

		// then
		assertThat(event.getEventName()).isEqualTo("취준생이여 올영으로 모여라 !!");
		assertThat(event.getExpiredTime()).isEqualTo(LocalDate.of(2025, 8, 31));
		assertThat(event.getAmount()).isEqualTo(3);
	}

	@Test
	void 이벤트_수량_감소_확인(){
		// given
		String eventName = "취준생이여 올영으로 모여라 !!";
		LocalDate expiredDate = LocalDate.of(2025, 8, 31);
		int amount = 3;
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();
		Event event = Event.builder()
			.eventName(eventName)
			.expiredTime(expiredDate)
			.amount(amount)
			.coupon(coupon)
			.build();
		//when
		event.subtractAmount();
		//then
		assertThat(event.getAmount()).isEqualTo(amount-1);
	}

	@Test
	void 이벤트_수량_소진_예외_확인(){
		// given
		String eventName = "취준생이여 올영으로 모여라 !!";
		LocalDate expiredDate = LocalDate.of(2025, 8, 31);
		int amount = 3;
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();
		Event event = Event.builder()
			.eventName(eventName)
			.expiredTime(expiredDate)
			.amount(amount)
			.coupon(coupon)
			.build();
		//when
		for (int i = 0; i < amount; i++) {
			event.subtractAmount(); // 정상 차감
		}

		//when && then
		assertThatThrownBy(event::subtractAmount)
			.isInstanceOf(EventException.class)
			.hasMessage(PromotionErrorCode.THIS_EVENT_IS_SOLD_OUT.getMessage());

	}

}
