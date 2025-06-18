package com.java.backend.global.redis;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
import com.java.backend.domain.member.point.service.PointService;
import com.java.backend.global.kafka.OutBoxStatus;
import com.java.backend.global.kafka.OutboxEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisOutboxWorker {

	private final StringRedisTemplate redisTemplate;
	private final PointService pointService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Scheduled(fixedDelay = 100)
	public void poll() {
		String json = redisTemplate.opsForList().rightPop("queue:outbox:point");
		if (json != null) {
			try {
				SavePointReqDto dto = objectMapper.readValue(json, SavePointReqDto.class);
				pointService.requestUpdateUserPointToOutbox(dto);
			} catch (Exception e) {

			}
		}
	}
}

