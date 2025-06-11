package com.java.backend.domain.point.service;

import com.java.backend.domain.point.dto.point_Dto;
import com.java.backend.domain.point.entity.point_OrderItem;
import com.java.backend.domain.point.repository.point_Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class point_Service {

    private final point_Repository repository;

    // SQS 관련 코드 주석 처리
    // private final SqsClient sqsClient;

    // @Value("${aws.sqs.queue-url}")
    // private String queueUrl;

    // 미국 리전: SQS 없이 바로 Aurora에 저장 (Write Forwarding 사용)
    public void saveSingleOrder(point_Dto dto) {
        point_OrderItem item = point_OrderItem.builder()
                .point(dto.getPoint())
                .orderId("US-" + UUID.randomUUID())  // 미국 리전용 접두어
                .build();

        repository.save(item); // Aurora로 직접 저장 (Write Forwarding 경유)
    }

    // 한국 리전: Aurora에 직접 저장
    public void saveDirectToAurora(point_Dto dto) {
        point_OrderItem item = point_OrderItem.builder()
                .point(dto.getPoint())
                .orderId("KR-" + UUID.randomUUID())  // 한국 리전용 접두어
                .build();

        repository.save(item);
    }

    // 전체 주문 목록 반환 (GET 요청용)
    public List<point_OrderItem> getAllOrders() {
        return repository.findAll();
    }
}
