package com.java.backend.domain.member.point.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
import com.java.backend.domain.member.point.dto.UpdatePointReqMessage;
import com.java.backend.domain.member.point.dto.UserPoint;
import com.java.backend.domain.member.user.entity.User;
import com.java.backend.domain.member.user.repository.OliveUserRepository;
import com.java.backend.global.kafka.KafkaTopic;
import com.java.backend.global.kafka.OutBoxStatus;
import com.java.backend.global.kafka.OutboxEvent;
import com.java.backend.global.kafka.OutboxRepoService;
import com.java.backend.global.redis.RedisMessagePublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

@Service
public class PointService {

    private static final Logger log = LoggerFactory.getLogger(PointService.class);

    private final ObjectMapper objectMapper;
    private final OutboxRepoService outboxRepoService;
    private final OliveUserRepository oliveUserRepository;
    private final RedisMessagePublisher redisMessagePublisher;

    public PointService(ObjectMapper objectMapper, OutboxRepoService outboxRepoService,
        OliveUserRepository oliveUserRepository, RedisMessagePublisher redisMessagePublisher) {
        this.objectMapper = objectMapper;
        this.outboxRepoService = outboxRepoService;
        this.oliveUserRepository = oliveUserRepository;
		this.redisMessagePublisher = redisMessagePublisher;
	}
    public void requestUpdateUserPoint(SavePointReqDto dto){
        redisMessagePublisher.enqueueSavePoint(dto);
    }

    @Transactional
    public void requestUpdateUserPointToOutbox(SavePointReqDto dto) throws JsonProcessingException {
        log.info("[PointService] 포인트 적립 요청 시작 - userId={}, price={}", dto.getUserId(), dto.getPrice());

        Integer point = calculatePoint(dto.getPrice());
        log.info("[PointService] 포인트 계산 완료 - point={}, price={}", point, dto.getPrice());

        UpdatePointReqMessage message = UpdatePointReqMessage.builder()
            .userId(dto.getUserId())
            .point(point)
            .build();

        String payload = serializePayload(message);
        String key = generateMessageKey(dto.getUserId());

        log.info("[PointService] Kafka Payload 준비 완료 - key={}, payload={}", key, payload);

        saveOutboxEvent(key, payload, KafkaTopic.USER_POINT);

        log.info("[PointService] Outbox 저장 완료 - topic={}, userId={}, point={}",
            KafkaTopic.USER_POINT.getTopic(), dto.getUserId(), point);
    }
    @Transactional
    public void updateUserPoint(UpdatePointReqMessage message) {
        log.info("[PointService] 사용자 포인트 업데이트 시작 - userId={}, point={}",
            message.getUserId(), message.getPoint());

        oliveUserRepository.updateUserPoint(message.getUserId(), message.getPoint());

        log.info("[PointService] 사용자 포인트 업데이트 완료");
    }

    public UserPoint viewUserPoint(Long userId){
        Integer point = oliveUserRepository.getUserPointByPk(userId).orElseThrow();
        UserPoint userPoint = new UserPoint(point);
        return userPoint;
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
