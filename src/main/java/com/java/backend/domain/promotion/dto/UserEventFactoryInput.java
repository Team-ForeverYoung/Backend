package com.java.backend.domain.promotion.dto;

import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;
import com.java.backend.domain.user.entity.User;

import lombok.Builder;

public class UserEventFactoryInput {
	private final Coupon coupon;
	private final Event event;
	private final User user;
	@Builder
	public UserEventFactoryInput(Coupon coupon, Event event, User user) {
		this.coupon = coupon;
		this.event = event;
		this.user = user;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public Event getEvent() {
		return event;
	}

	public User getUser() {
		return user;
	}
}
