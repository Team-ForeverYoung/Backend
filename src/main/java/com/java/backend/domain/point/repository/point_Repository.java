package com.java.backend.domain.point.repository;

import com.java.backend.domain.point.entity.point_OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface point_Repository extends JpaRepository<point_OrderItem, Long> {
}
