package com.java.backend.domain.member.point.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
import com.java.backend.domain.member.point.dto.UpdatePointReqMessage;
import com.java.backend.domain.member.user.repository.OliveUserRepository;
import com.java.backend.global.kafka.KafkaTopic;
import com.java.backend.global.kafka.OutBoxStatus;
import com.java.backend.global.kafka.OutboxEvent;
import com.java.backend.global.kafka.OutboxRepoService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointService {

    private final ObjectMapper objectMapper;
    private final OutboxRepoService outboxRepoService;
    private final OliveUserRepository oliveUserRepository;

    public PointService(ObjectMapper objectMapper, OutboxRepoService outboxRepoService,
        OliveUserRepository oliveUserRepository) {
        this.objectMapper = objectMapper;
        this.outboxRepoService = outboxRepoService;
		this.oliveUserRepository = oliveUserRepository;
	}

    // 미국 리전: SQS 없이 바로 Aurora에 저장 (Write Forwarding 사용)
    public void requestUpdateUserPoint(SavePointReqDto dto) throws JsonProcessingException {
        try {
            Integer point = calculatePoint(dto.getPrice());
            UpdatePointReqMessage message = UpdatePointReqMessage.builder()
                .userId(dto.getUserId())
                .point(point)
                .build();
            String payload = serializePayload(message);
            String key = generateMessageKey(dto.getUserId());
            saveOutboxEvent(key, payload, KafkaTopic.USER_POINT);
        } catch (JsonParseException e) {
            throw e;
        }
    }

    public void updateUserPoint(UpdatePointReqMessage message){
        oliveUserRepository.updateUserPoint(message.getUserId(), message.getPoint());
    }


    private Integer calculatePoint(Integer price) {
        return price * 10 / 100;
    }

    private String serializePayload(Object dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    private String generateMessageKey(Long userId) {
        return String.valueOf(userId);
    }

    private void saveOutboxEvent(String key, String payload, KafkaTopic topic) {
        OutboxEvent outboxEvent = OutboxEvent.builder()
            .topic(topic.getTopic())
            .messageKey(key)
            .payload(payload)
            .status(OutBoxStatus.READY)
            .createdAt(LocalDateTime.now())
            .build();
        outboxRepoService.saveEvent(outboxEvent);
    }


}





// // 한국 리전: Aurora에 직접 저장
// public void saveDirectToAurora(SavePointReqDto dto) {
//     // point_OrderItem item = point_OrderItem.builder()
//     //         .point(dto.getPoint())
//     //         .orderId("KR-" + UUID.randomUUID())  // 한국 리전용 접두어
//     //         .build();
//     //
//     // repository.save(item);
//
// }

//
// // 전체 주문 목록 반환 (GET 요청용)
// public List<point_OrderItem> getAllOrders() {
//     return repository.findAll();
// }