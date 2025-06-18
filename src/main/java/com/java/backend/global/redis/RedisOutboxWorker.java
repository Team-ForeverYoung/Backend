package com.java.backend.global.redis;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.backend.domain.member.point.dto.SavePointReqDto;
import com.java.backend.domain.member.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisOutboxWorker implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RedisOutboxWorker.class);

	private final StringRedisTemplate redisTemplate;
	private final PointService pointService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	private final ExecutorService executorService = Executors.newFixedThreadPool(16);

	private static final String MAIN_QUEUE = "queue:outbox:point";
	private static final String BACKUP_QUEUE = "queue:outbox:processing";
	private static final String RETRY_QUEUE = "queue:outbox:retry";

	@Override
	public void run(String... args) throws Exception {
		log.info("[RedisOutboxWorker] Redis 워커 실행 시작");
		while (true) {
			try {
				String json = redisTemplate.opsForList()
					.rightPopAndLeftPush(MAIN_QUEUE, BACKUP_QUEUE, 1, TimeUnit.SECONDS);

				if (json != null) {
					executorService.submit(() -> {
						try {
							SavePointReqDto dto = objectMapper.readValue(json, SavePointReqDto.class);
							pointService.requestUpdateUserPointToOutbox(dto);

							redisTemplate.opsForList().remove(BACKUP_QUEUE, 1, json);
							log.info("✅ 처리 성공 - userId={} price={}", dto.getUserId(), dto.getPrice());
						} catch (Exception e) {
							log.error("❌ 처리 실패 - 재시도 큐로 이동", e);
							redisTemplate.opsForList().leftPush(RETRY_QUEUE, json);
							redisTemplate.opsForList().remove(BACKUP_QUEUE, 1, json);
						}
					});
				}
			} catch (Exception e) {
				log.error("[RedisOutboxWorker] Redis Poll 실패", e);
				Thread.sleep(500);
			}
		}
	}

	@Scheduled(fixedDelay = 5000)
	public void retryFailedMessages() {
		try {
			String json = redisTemplate.opsForList().rightPop(RETRY_QUEUE);
			if (json != null) {
				redisTemplate.opsForList().leftPush(MAIN_QUEUE, json);
				log.info("🔁 재시도 메시지 복구 - {}", json);
			}
		} catch (Exception e) {
			log.error("[RedisOutboxWorker] 재시도 복구 실패", e);
		}
	}
}