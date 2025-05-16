package com.java.backend.domain.olive_order.service;

import com.java.backend.domain.olive_order.dto.olive_OrderItemDto;
import com.java.backend.domain.olive_order.entity.olive_OrderItem;
import com.java.backend.domain.olive_order.repository.olive_OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class olive_OrderService {

    private final olive_OrderItemRepository repository;

    public void saveOrder(List<olive_OrderItemDto> orderList) {
        List<olive_OrderItem> items = orderList.stream()
                .map(dto -> olive_OrderItem.builder()
                        .productName(dto.getProductName())
                        .salePrice(dto.getSalePrice())
                        .build())
                .collect(Collectors.toList());
        repository.saveAll(items);
    }

    public List<olive_OrderItem> getAllOrders() {
        return repository.findAll();
    }
}
