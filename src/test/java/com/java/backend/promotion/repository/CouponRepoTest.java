package com.java.backend.promotion.repository;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.exception.CouponException;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.repository.CouponRepository;

@ExtendWith(MockitoExtension.class)
public class CouponRepoTest {
	@Mock
	private CouponRepository couponRepository;
	@InjectMocks
	private CouponRepoService couponRepoService;

	@Test
	void 쿠폰_저장_테스트(){
		//given
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();
		//mocking
		when(couponRepository.save(coupon)).thenReturn(coupon);

		//when
		Coupon savedCoupon = couponRepoService.registerCoupon(coupon);

		//then
		Assertions.assertEquals(coupon.getBenefit(), savedCoupon.getBenefit());
		Assertions.assertEquals(coupon.getName(), savedCoupon.getName());
		Assertions.assertEquals(coupon.getExpiredTime(), savedCoupon.getExpiredTime());
	}

	@Test
	void 쿠폰_조회_테스트(){
		//given
		Coupon coupon = Coupon.builder()
			.name("취업하기 쿠폰")
			.expiredTime(LocalDate.of(2025, 12, 31))
			.benefit("취준시 취업 확률 500% 증가!")
			.build();
		//mocking
		when(couponRepository.findById(any())).thenReturn(Optional.ofNullable(coupon));

		//when
		Coupon foundCoupon= couponRepoService.getCoupon(any());

		//then
		Assertions.assertEquals(Objects.requireNonNull(coupon).getBenefit(), foundCoupon.getBenefit());
		Assertions.assertEquals(coupon.getName(), foundCoupon.getName());
		Assertions.assertEquals(coupon.getExpiredTime(), foundCoupon.getExpiredTime());
	}

	@Test
	void 코폰_조회_예외_테스트(){
		//given
		Long couponId = 1L;
		//mocking
		when(couponRepository.findById(couponId)).thenReturn(Optional.empty());

		//then
		Assertions.assertThrows(CouponException.couponNotFoundException.class, ()->{
			couponRepoService.getCoupon(couponId);
		});
	}

}
