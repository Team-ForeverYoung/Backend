// package com.java.backend.domain.promotion.service;
//
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.event.TransactionalEventListener;
//
// import com.java.backend.domain.promotion.dto.EventResultMessage;
// import com.java.backend.domain.promotion.message.PromotionEventProducer;
//
// @Component
// public class PromotionListener {
// 	private final PromotionEventProducer promotionEventProducer;
//
// 	public PromotionListener(PromotionEventProducer promotionEventProducer) {
// 		this.promotionEventProducer = promotionEventProducer;
// 	}
//
// 	@TransactionalEventListener
// 	public void handlePromotionResult(EventResultMessage message){
// 		promotionEventProducer.promotionResultProducer(message);
// 	}
// }
