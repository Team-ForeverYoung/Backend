package com.java.backend.domain.member.point.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
import com.java.backend.domain.member.point.dto.UserPoint;
import com.java.backend.domain.member.point.entity.point_OrderItem;
import com.java.backend.domain.member.point.service.PointService;
import com.java.backend.domain.promotion.code.PromotionCode;
import com.java.backend.global.code.ResponseApiCode;
import com.java.backend.global.code.RestApiResponse;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;
    private static final Logger log = LoggerFactory.getLogger(PointController.class);
    // 미국 리전 (SQS → Lambda → Aurora)
    @PostMapping
    public ResponseEntity<ResponseApiCode> saveOrder(@RequestBody SavePointReqDto dto) throws JsonProcessingException {
        pointService.requestUpdateUserPoint(dto);
        RestApiResponse restApiResponse = new RestApiResponse(PromotionCode.USER_EVENT_CREATED_SUCCESS);
        return ResponseEntity.ok(restApiResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseApiCode> viewPoint(@PathVariable("userId") Long userId){
        UserPoint userPoint = pointService.viewUserPoint(userId);
        RestApiResponse restApiResponse = new RestApiResponse(PromotionCode.USER_EVENT_CREATED_SUCCESS,userPoint);
        return ResponseEntity.ok(restApiResponse);
    }

    // @GetMapping
    // public List<point_OrderItem> getOrders() {
    //     return service.getAllOrders();
    // }
    //
    // // 한국 리전 (API 직접 → Aurora)
    // @PostMapping("/kr-direct")
    // public void saveOrderDirectToKrAurora(@RequestBody SavePointReqDto dto) {
    //     service.saveDirectToAurora(dto);
    // }
}
