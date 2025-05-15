package com.java.backend.domain.user.entity;

import com.java.backend.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private Integer point;

	@Column
	private String country;

	@Builder
	public User(String userId, String name, String email, String passWord, Integer point, String country) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = passWord;
		this.point = point;
		this.country = country;
	}

	public User() {

	}

	public String getEmail() {
		return email;
	}

	public String getUserId() { return userId; }

	public String getPassWord() {
		return password;
	}

	public Integer getPoint() {
		return point;
	}

	public String getCountry() {
		return country;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
