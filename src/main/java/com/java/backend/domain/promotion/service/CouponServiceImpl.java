package com.java.backend.domain.promotion.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.factory.CouponFactory;
import com.java.backend.domain.promotion.repository.CouponRepoService;

@Service
public class CouponServiceImpl implements CouponService{
	private final CouponRepoService couponRepoService;
	public CouponServiceImpl(CouponRepoService couponRepoService) {
		this.couponRepoService = couponRepoService;
	}

	@Override
	public Coupon createCoupon(CouponCreateRequestDto couponCreateRequestDto) {
		Coupon coupon = CouponFactory.createFromAdminRequest(couponCreateRequestDto);
		return couponRepoService.registerCoupon(coupon);
	}

}
