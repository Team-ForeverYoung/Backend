package com.java.backend.domain.movie.entity;

import com.java.backend.domain.theater.entity.Cinema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "screenings")
public class Screening {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "cinema_id", nullable = false)
	private Cinema cinema;


}
