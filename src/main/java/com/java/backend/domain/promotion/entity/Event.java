package com.java.backend.domain.promotion.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

	public Event(String eventName, LocalDate expiredTime, Integer amount, Coupon coupon) {
		this.eventName = eventName;
		this.expiredTime = expiredTime;
		this.amount = amount;
		this.coupon = coupon;
	}

	public void subtractAmount(){
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
}
