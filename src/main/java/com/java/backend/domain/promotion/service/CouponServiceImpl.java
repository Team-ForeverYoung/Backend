package com.java.backend.domain.promotion.service;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.dto.CouponReservationRequest;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.factory.CouponFactory;
import com.java.backend.domain.promotion.repository.CouponRepoService;
import com.java.backend.domain.promotion.stub.StubUserRepository;
import com.java.backend.domain.user.entity.User;

@Service
public class CouponServiceImpl implements CouponService{
	private final CouponRepoService couponRepoService;
	public CouponServiceImpl(CouponRepoService couponRepoService) {
		this.couponRepoService = couponRepoService;
	}

	@Override
	public void createCoupon(CouponCreateRequestDto couponCreateRequestDto) {
		Coupon coupon = CouponFactory.createFromAdminRequest(couponCreateRequestDto);
		couponRepoService.registerCoupon(coupon);
	}

}
