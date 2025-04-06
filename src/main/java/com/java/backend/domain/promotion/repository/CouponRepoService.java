package com.java.backend.domain.promotion.repository;

import java.util.Optional;

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

	public Coupon getCoupon(Long couponId){
		Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
		//ToDo: 예외처리
		Coupon coupon = optionalCoupon.orElseThrow();
		return coupon;
		}
	}

