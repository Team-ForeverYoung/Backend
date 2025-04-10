package com.java.backend.domain.promotion.entity;

import java.time.LocalDate;

import com.java.backend.domain.promotion.code.PromotionErrorCode;
import com.java.backend.domain.promotion.exception.EventException;
import com.java.backend.global.code.ResponseApiCode;
import com.java.backend.global.exception.ExceptionMetaData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String eventName;

	@Column
	private LocalDate expiredTime;

	@Column
	private Integer amount;
	@ManyToOne
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;


	public Event() {
	}
	@Builder
	public Event(String eventName, LocalDate expiredTime, Integer amount, Coupon coupon) {
		this.eventName = eventName;
		this.expiredTime = expiredTime;
		this.amount = amount;
		this.coupon = coupon;
	}

	public void subtractAmount(){
		if(amount <= 0){
			ExceptionMetaData exceptionMetaData = createExceptionMetaData(PromotionErrorCode.THIS_EVENT_IS_SOLD_OUT);
			throw new EventException.eventSoledOutException(exceptionMetaData);
		}
		Integer amount = this.amount - 1 ;
		this.setAmount(amount);
	}

	private void setAmount(Integer amount) {
		this.amount = amount;
	}




	public String getEventName() {
		return eventName;
	}

	public LocalDate getExpiredTime() {
		return expiredTime;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public Long getId() {
		return id;
	}

	public Integer getAmount() {
		return amount;
	}


	private ExceptionMetaData createExceptionMetaData(ResponseApiCode responseApiCode){
		return ExceptionMetaData.builder()
			.responseApiCode(responseApiCode)
			.className(this.getClass().getName())
			.build();
	}

}
