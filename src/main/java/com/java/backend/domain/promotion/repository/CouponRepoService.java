package com.java.backend.domain.promotion.repository;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.code.PromotionErrorCode;
import com.java.backend.domain.promotion.entity.Coupon;
import com.java.backend.domain.promotion.exception.CouponException;
import com.java.backend.global.exception.ExceptionMetaData;

@Service
public class CouponRepoService {
	private final CouponRepository couponRepository;

	public CouponRepoService(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}

	public Coupon registerCoupon(Coupon coupon){
		return couponRepository.save(coupon);

	}

	public Coupon getCoupon(Long couponId){
		return couponRepository.findById(couponId)
			.orElseThrow(() -> new CouponException.couponNotFoundException(
				ExceptionMetaData.builder()
					.responseApiCode(PromotionErrorCode.COUPON_NOT_FOUND)
					.className(this.getClass().getName())
					.build()
			));
	}

	}

