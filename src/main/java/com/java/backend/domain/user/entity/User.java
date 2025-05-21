package com.java.backend.domain.user.entity;

import jakarta.persistence.*;
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
	private String pass_word;

	@Column
	private Integer point;

	@Column
	private String country;

	@Builder
	public User(String userId, String name, String email, String passWord, Integer point, String country) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.pass_word = passWord;
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
		return pass_word;
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
