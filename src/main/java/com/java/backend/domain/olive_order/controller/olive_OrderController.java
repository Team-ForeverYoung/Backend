package com.java.backend.domain.olive_order.controller;

import com.java.backend.domain.olive_order.dto.olive_OrderItemDto;
import com.java.backend.domain.olive_order.entity.olive_OrderItem;
import com.java.backend.domain.olive_order.service.olive_OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class olive_OrderController {

    private final olive_OrderService service;

    @PostMapping
    public void saveOrder(@RequestBody List<olive_OrderItemDto> orderList) {
        service.saveOrder(orderList);
    }

    @GetMapping
    public List<olive_OrderItem> getOrders() {
        return service.getAllOrders();
    }
}
