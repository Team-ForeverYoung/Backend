package com.java.backend.global.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
@Configuration
public class KafkaTemplateConfig {

	private static final String BOOTSTRAP_SERVER = "kafka.kafka.svc.cluster.local:9092";

	@Bean
	public ProducerFactory<String, EventJoinMessage> eventJoinMessageProducerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, EventJoinMessage> eventJoinMessageKafkaTemplate() {
		return new KafkaTemplate<>(eventJoinMessageProducerFactory());
	}
}
