package com.java.backend.global.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class RedisConfig {
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	// @Bean
	// public Executor redisWorkerExecutor() {
	// 	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	// 	executor.setCorePoolSize(8);
	// 	executor.setMaxPoolSize(16);
	// 	executor.setQueueCapacity(1000);
	// 	executor.setThreadNamePrefix("redis-worker-");
	// 	executor.initialize();
	// 	return executor;
	// }
}

