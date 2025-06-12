package com.java.backend.domain.member.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Builder
@Getter
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
	private String passWord;

	@Column
	private Integer point;

	@Column
	private String country;


	public User() {

	}


}
