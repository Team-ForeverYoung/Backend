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
	private Long eventName;

	@Column
	private LocalDate expiredTime;
	@ManyToOne
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;


	public Event() {
	}

	public Event(Long eventName, LocalDate expiredTime, Coupon coupon) {
		this.eventName = eventName;
		this.expiredTime = expiredTime;
		this.coupon = coupon;
	}


	public Long getEventName() {
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
}
