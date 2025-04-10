package com.java.backend.domain.order.entity;

public enum OrderState {
	PENDING,          // 주문 접수 (결제 전)
	PAID,             // 결제 완료
	SHIPPING,         // 배송 준비 중
	SHIPPED,          // 배송 중
	DELIVERED,        // 배송 완료
	CANCELLED,        // 주문 취소
	REFUNDED;         // 환불 완료
}
