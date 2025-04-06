package com.java.backend.domain.promotion.repository;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.entity.Coupon;

@Service
public class CouponRepoService {
	private final CouponRepository couponRepository;

	public CouponRepoService(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	public void registerCoupon(Coupon coupon){
		couponRepository.save(coupon);
	}
}
