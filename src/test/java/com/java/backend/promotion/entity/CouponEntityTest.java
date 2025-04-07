package com.java.backend.promotion.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.java.backend.domain.promotion.entity.Coupon;

public class CouponEntityTest {

	@Test
	void 쿠폰_생성시_기본_값_확인() {
		// given
		String couponName = "취업하기 쿠폰";
		LocalDate expiredTime = LocalDate.of(2025, 4, 11);
		String benefit = "취업시 취업 확률 500% 증가!";

		// when
		Coupon coupon = Coupon.builder()
			.name(couponName)
			.expiredTime(expiredTime)
			.benefit(benefit)
			.build();

		// then
		assertThat(coupon.getName()).isEqualTo("취업하기 쿠폰");
		assertThat(coupon.getExpiredTime()).isEqualTo(LocalDate.of(2025, 4, 11));
		assertThat(coupon.getBenefit()).isEqualTo("취업시 취업 확률 500% 증가!");
	}
}
