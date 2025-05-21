package com.java.backend.domain.promotion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.backend.domain.promotion.entity.UserEvent;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent,Long> {
	@Query("SELECT ue FROM UserEvent ue WHERE ue.user.id=:userId AND ue.event.id=:eventId")
	Optional<UserEvent> findByUserAndEvent (@Param("userId")Long userId, @Param("eventId")Long eventId);
}
