package com.java.backend.domain.promotion.service;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.dto.CouponReservationRequest;
import com.java.backend.domain.promotion.entity.Coupon;

public interface CouponService {
	public Coupon createCoupon(CouponCreateRequestDto couponCreateRequestDto);
}
