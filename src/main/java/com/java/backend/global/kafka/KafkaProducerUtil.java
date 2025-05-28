package com.java.backend.global.kafka;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventResultMessage;

@Component
public class KafkaProducerUtil {
	private final KafkaTemplate<String, EventJoinMessage> eventJoinKafkaTemplate;
	private final KafkaTemplate<String, EventResultMessage> eventResultKafkaTemplate;
	public KafkaProducerUtil(KafkaTemplate<String, EventJoinMessage> kafkaTemplate,
		KafkaTemplate<String, EventJoinMessage> eventJoinKafkaTemplate,
		KafkaTemplate<String, EventResultMessage> eventResultKafkaTemplate) {
		this.eventJoinKafkaTemplate = eventJoinKafkaTemplate;
		this.eventResultKafkaTemplate = eventResultKafkaTemplate;
	}

	public void sendEventJoinMessage(String topic, String key, EventJoinMessage value) {
		CompletableFuture<SendResult<String,EventJoinMessage>> future = eventJoinKafkaTemplate.send(topic, key, value );

		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		});

	}

	public void sendEventResultMessage(String topic, String key, EventResultMessage value){
		CompletableFuture<SendResult<String,EventResultMessage>> future = eventResultKafkaTemplate.send(topic,key,value);

		future.whenComplete( (result,ex) -> {
			if (ex == null) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}
		});
	}
}
