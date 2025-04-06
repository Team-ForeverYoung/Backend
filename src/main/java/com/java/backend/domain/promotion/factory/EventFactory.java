package com.java.backend.domain.promotion.factory;

import java.time.LocalDate;

import com.java.backend.domain.promotion.dto.EventCreateRequestDto;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.entity.Event;

public class EventFactory {
	public static Event createFromAdminRequest(EventCreateRequestDto dto){
		return new Event(dto.getEventName(),dto.getExpiredDate(), dto.getAmount(),dto.getCoupon());
	}
}
