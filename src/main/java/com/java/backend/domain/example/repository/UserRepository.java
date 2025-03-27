package com.java.backend.domain.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.backend.domain.example.dto.UserInfoResponseDto;
import com.java.backend.domain.example.entity.TestUser;

@Repository
public interface UserRepository extends JpaRepository<TestUser,Long> {
	@Query(" SELECT new com.java.backend.domain.example.dto.UserInfoResponseDto(user.name,user.age) "
		+ "FROM TestUser user "
		+ "WHERE user.id = :userId")
	UserInfoResponseDto findUserById(@Param("userId") Long userId);

}
