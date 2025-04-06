package com.java.backend.domain.promotion.dto;

import java.time.LocalDate;

public class CouponCreateRequestDto {
	private String couponName;
	private LocalDate expiredTime;
	private String benefit;

	public String getCouponName() {
		return this.couponName;
	}

	public LocalDate getExpiredTime() {
		return this.expiredTime;
	}

	public String getBenefit() {
		return this.benefit;
	}

	public CouponCreateRequestDto() {}
	public CouponCreateRequestDto(String couponName, LocalDate expiredTime, String benefit) {
		this.couponName = couponName;
		this.expiredTime = expiredTime;
		this.benefit = benefit;
	}
}
