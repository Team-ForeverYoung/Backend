package com.java.backend.domain.promotion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.backend.domain.promotion.entity.Coupon;
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {


}
