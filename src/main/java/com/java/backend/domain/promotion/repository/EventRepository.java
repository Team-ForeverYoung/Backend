package com.java.backend.domain.promotion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.backend.domain.promotion.entity.Event;

import jakarta.persistence.LockModeType;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT e FROM Event e WHERE e.id = :id")
	Optional<Event> findByIdForUpdate(@Param("id") Long id);
}
