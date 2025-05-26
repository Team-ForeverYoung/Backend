package com.java.backend.domain.promotion.message;

import com.java.backend.domain.promotion.dto.EventJoinMessage;

public interface PromotionEventProducer {
	public void promotionEventJoinProducer(EventJoinMessage eventJoinMessage);
}
