package com.java.backend.domain.olive_order.repository;

import com.java.backend.domain.olive_order.entity.olive_OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface olive_OrderItemRepository extends JpaRepository<olive_OrderItem, Long> {
}
