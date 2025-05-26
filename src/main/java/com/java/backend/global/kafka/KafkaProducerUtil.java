package com.java.backend.global.kafka;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class KafkaProducerUtil {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducerUtil(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String topic, String key, String value) {
		System.out.println("send 진입:  " + topic + key + value);
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, value);

		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		});

	}
}
