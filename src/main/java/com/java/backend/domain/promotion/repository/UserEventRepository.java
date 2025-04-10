package com.java.backend.domain.promotion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.backend.domain.promotion.entity.UserEvent;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent,Long> {
}
