package com.java.backend.domain.olive_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.java.backend.domain.olive_user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OliveUserRepository extends JpaRepository<User, String> {
    @Query("SELECT u.password FROM User u WHERE u.userId = :userId")
    String findPassWordByUserId(@Param("userId") String userId);
    Optional<User> findByUserId(String userId);
}
