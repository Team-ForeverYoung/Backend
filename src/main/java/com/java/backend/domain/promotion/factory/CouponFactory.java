package com.java.backend.domain.promotion.factory;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;

public class CouponFactory {

	public static Coupon createFromAdminRequest(CouponCreateRequestDto dto){
		return new Coupon(dto.getCouponName(),dto.getBenefit(),dto.getExpiredTime());
	}

}
