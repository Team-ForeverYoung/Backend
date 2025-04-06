package com.java.backend.domain.promotion.factory;

import java.time.LocalDate;

import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CouponFactory {

	public static Coupon createFromAdminRequest(CouponCreateRequestDto dto){
		return new Coupon(dto.getCouponName(),dto.getBenefit(),dto.getExpiredTime());
	}

}
