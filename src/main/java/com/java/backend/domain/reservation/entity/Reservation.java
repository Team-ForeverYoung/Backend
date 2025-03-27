package com.java.backend.domain.reservation.entity;

import org.springframework.lang.Nullable;

import com.java.backend.domain.theater.entity.Seat;
import com.java.backend.domain.user.entity.User;
import com.java.backend.global.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "seat_id", nullable = false)
	private Seat seat;


}
