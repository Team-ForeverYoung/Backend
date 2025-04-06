package com.java.backend.domain.promotion.entity;

import com.java.backend.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "userevents")
public class UserEvent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public Event getEvent() {
		return event;
	}

	public User getUser() {
		return user;
	}

	public UserEvent() {
	}
	@Builder
	public UserEvent(Coupon coupon, Event event, User user) {
		this.coupon = coupon;
		this.event = event;
		this.user = user;
	}
}
