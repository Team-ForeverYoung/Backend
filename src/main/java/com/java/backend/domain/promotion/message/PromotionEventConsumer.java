package com.java.backend.domain.promotion.message;

import com.java.backend.domain.promotion.dto.EventJoinMessage;

public interface PromotionEventConsumer {
	public void promotionEventJoinConsumer(EventJoinMessage Message);
}
