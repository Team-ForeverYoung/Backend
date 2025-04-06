package com.java.backend.domain.promotion.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.backend.domain.promotion.code.PromotionCode;
import com.java.backend.domain.promotion.dto.CouponCreateRequestDto;
import com.java.backend.domain.promotion.service.CouponService;
import com.java.backend.global.code.RestApiResponse;

@RestController
@RequestMapping("/api/v1/admin/coupon")
public class CouponAdminController {
	private final CouponService couponService;

	public CouponAdminController(CouponService couponService) {
		this.couponService = couponService;
	}
	@PostMapping
	public ResponseEntity<RestApiResponse> registerAdminCoupon(@RequestBody CouponCreateRequestDto dto){
		couponService.createCoupon(dto);
		RestApiResponse restApiResponse = new RestApiResponse(PromotionCode.COUPON_CREATED_SUCCESS);
		return ResponseEntity.ok(restApiResponse);
	}


}
