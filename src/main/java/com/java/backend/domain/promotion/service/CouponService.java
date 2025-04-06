package com.java.backend.domain.promotion.service;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.dto.CouponReservationRequest;

public interface CouponService {
	public void createCoupon(CouponCreateRequestDto couponCreateRequestDto);
}
