package com.java.backend.domain.promotion.factory;

import com.java.backend.domain.promotion.dto.UserEventFactoryInput;
import com.java.backend.domain.promotion.entity.UserEvent;

public class UserEventFactory {
	public static UserEvent createFromUserRequest(UserEventFactoryInput dto){
		return UserEvent.builder()
			.coupon(dto.getCoupon())
			.event(dto.getEvent())
			.user(dto.getUser()).build();
	}
}
