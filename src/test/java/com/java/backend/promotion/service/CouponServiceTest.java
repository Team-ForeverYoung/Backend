package com.java.backend.promotion.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.repository.CouponRepository;
import com.java.backend.domain.promotion.service.CouponServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

	@Mock
	private CouponRepoService couponRepoService; // CouponRepoService를 mock으로 설정

	@InjectMocks
	private CouponServiceImpl couponService; // couponService에 mock된 의존성 주입

	@Test
	void 쿠폰_생성_서비스_테스트(){
		// given
		String couponName = "취업하기 쿠폰";
		LocalDate expiredTime = LocalDate.of(2025, 12, 31);
		String benefit = "취준시 취업 확률 500% 증가!";

		CouponCreateRequestDto dto = new CouponCreateRequestDto(couponName, expiredTime, benefit);

		// Coupon 객체를 dto를 사용해 생성 (예상 객체)
		Coupon expectedCoupon = Coupon.builder()
			.name(couponName)
			.expiredTime(expiredTime)
			.benefit(benefit)
			.build();

		// 실제 서비스에서 생성될 객체 모킹
		when(couponRepoService.registerCoupon(any(Coupon.class))).thenReturn(expectedCoupon);

		// when
		Coupon savedCoupon = couponService.createCoupon(dto);

		// then
		assertThat(savedCoupon).isNotNull(); // null 체크 추가
		assertThat(savedCoupon.getExpiredTime()).isEqualTo(expectedCoupon.getExpiredTime());
		assertThat(savedCoupon.getName()).isEqualTo(expectedCoupon.getName());
		assertThat(savedCoupon.getBenefit()).isEqualTo(expectedCoupon.getBenefit());
	}



}
