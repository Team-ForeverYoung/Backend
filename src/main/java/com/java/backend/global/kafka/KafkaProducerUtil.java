package com.java.backend.global.kafka;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import com.java.backend.domain.promotion.dto.EventJoinMessage;

@Component
public class KafkaProducerUtil {
	private final KafkaTemplate<String, EventJoinMessage> kafkaTemplate;
	public KafkaProducerUtil(KafkaTemplate<String, EventJoinMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String topic, String key, EventJoinMessage value) {
		CompletableFuture<SendResult<String,EventJoinMessage>> future = kafkaTemplate.send(topic, key, value );

		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		});

	}
}
