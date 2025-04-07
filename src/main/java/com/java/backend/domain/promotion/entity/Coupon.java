package com.java.backend.domain.promotion.entity;

import java.time.LocalDate;

import com.java.backend.domain.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String benefit;

	@Column
	private LocalDate expiredTime;
	@Builder
	public Coupon(String name, String benefit, LocalDate expiredTime) {
		this.name = name;
		this.benefit = benefit;
		this.expiredTime = expiredTime;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBenefit() {
		return benefit;
	}
	public LocalDate getExpiredTime() {
		return expiredTime;
	}


}
