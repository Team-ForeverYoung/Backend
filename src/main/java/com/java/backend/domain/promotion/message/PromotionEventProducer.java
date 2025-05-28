package com.java.backend.domain.promotion.message;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventResultMessage;

public interface PromotionEventProducer {
	public void promotionEventJoinProducer(EventJoinMessage eventJoinMessage);
	public void promotionResultProducer(EventResultMessage eventResultMessage);
}
