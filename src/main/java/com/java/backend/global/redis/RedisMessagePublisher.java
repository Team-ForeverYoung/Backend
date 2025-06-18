package com.java.backend.global.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
@Component
public class RedisMessagePublisher {

	private final StringRedisTemplate redisTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public RedisMessagePublisher(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// 회원 포인트 적립
	public void enqueueSavePoint(SavePointReqDto request) {
		try {
			String json = objectMapper.writeValueAsString(request);
			redisTemplate.opsForList().leftPush("queue:outbox:point", json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Redis push 실패", e);
		}
	}
}
