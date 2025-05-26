package com.java.backend.global.kafka;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Component
public class KafkaProducerUtil {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducerUtil(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String topic, String key, String value) {
		kafkaTemplate.send(topic, key, value);
	}
}
