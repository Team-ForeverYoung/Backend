package com.java.backend.domain.member.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.java.backend.domain.member.user.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OliveUserRepository extends JpaRepository<User, String> {
    @Query("SELECT u.passWord FROM User u WHERE u.userId = :userId")
    String findPassWordByUserId(@Param("userId") String userId);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<User> findByUserPk(Long userId);

    @Modifying
    @Query("UPDATE User u SET u.point = u.point + :point WHERE u.id = :userId")
    void updateUserPoint(@Param("userId") Long userId, @Param("point") Integer point);

    @Query("SELECT u.point FROM User u WHERE u.id = :userId ")
    Optional<Integer> getUserPointByPk(@Param("userId")Long userId);

}
