package com.java.backend.domain.order.entity;

import com.java.backend.domain.member.user.entity.User;
import com.java.backend.global.entity.BaseEntity;

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
@Table(name = "orders")
public class Order extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private OrderState orderState;

	public Order() {

	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	@Builder
	public Order(User user, OrderState orderState) {
		this.user = user;
		this.orderState = orderState;
	}
}
