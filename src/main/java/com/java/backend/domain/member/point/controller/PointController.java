package com.java.backend.domain.member.point.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
import com.java.backend.domain.member.point.entity.point_OrderItem;
import com.java.backend.domain.member.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point")
@RequiredArgsConstructor
public class PointController {

    private final PointService service;

    // 미국 리전 (SQS → Lambda → Aurora)
    @PostMapping
    public void saveOrder(@RequestBody SavePointReqDto dto) throws JsonProcessingException {
        service.requestUpdateUserPoint(dto);
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
