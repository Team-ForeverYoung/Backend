package com.java.backend.domain.promotion.dto;

import java.time.LocalDate;

import com.java.backend.domain.promotion.entity.Coupon;

public class EventCreateRequestDto {
	private String eventName;
	private LocalDate expiredDate;
	private Integer amount;
	private Coupon coupon;
	private Long couponId;


	public EventCreateRequestDto() {}

	public EventCreateRequestDto(String eventName, LocalDate expiredDate, Integer amount, Long couponId) {
		this.eventName = eventName;
		this.expiredDate = expiredDate;
		this.amount = amount;
		this.couponId = couponId;
	}

	private EventCreateRequestDto(String eventName, LocalDate expiredDate, Integer amount, Coupon coupon) {
		this.eventName = eventName;
		this.expiredDate = expiredDate;
		this.amount = amount;
		this.coupon = coupon;
	}

	public static EventCreateRequestDto of(EventCreateRequestDto dto, Coupon coupon) {
		return new EventCreateRequestDto(dto.getEventName(), dto.getExpiredDate(), dto.getAmount(), coupon);
	}

	public Long getCouponId() {
		return couponId;
	}

	public String getEventName() {
		return eventName;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public Coupon getCoupon() {
		return coupon;
	}
}
