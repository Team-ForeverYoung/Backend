package com.java.backend.domain.promotion.dto;

public class CouponReservationRequest {
	private Long userId;
	private String couponName;

	public Long getUserId() {
		return userId;
	}
	public CouponReservationRequest() {
	}

	public CouponReservationRequest(Long userId) {
		this.userId = userId;
	}
}
