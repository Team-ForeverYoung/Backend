package com.java.backend.domain.promotion.stub;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.backend.domain.user.entity.User;

@Repository
public interface StubUserRepository extends JpaRepository<User,Long> {
	Optional<User> findById(Long id);
}
