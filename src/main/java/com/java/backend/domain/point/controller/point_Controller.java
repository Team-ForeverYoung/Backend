package com.java.backend.domain.point.controller;

import com.java.backend.domain.point.dto.point_Dto;
import com.java.backend.domain.point.entity.point_OrderItem;
import com.java.backend.domain.point.service.point_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/point")
@RequiredArgsConstructor
public class point_Controller {

    private final point_Service service;

    // 미국 리전 (SQS → Lambda → Aurora)
    @PostMapping
    public void saveOrder(@RequestBody point_Dto dto) {
        service.saveSingleOrder(dto);
    }

    @GetMapping
    public List<point_OrderItem> getOrders() {
        return service.getAllOrders();
    }

    // 한국 리전 (API 직접 → Aurora)
    @PostMapping("/kr-direct")
    public void saveOrderDirectToKrAurora(@RequestBody point_Dto dto) {
        service.saveDirectToAurora(dto);
    }
}
