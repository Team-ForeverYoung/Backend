package com.java.backend.global.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.java.backend.domain.promotion.dto.EventJoinMessage;
import com.java.backend.domain.promotion.dto.EventResultMessage;

@Configuration
public class KafkaTemplateConfig {

	private static final String BOOTSTRAP_SERVER = "kafka.kafka.svc.cluster.local:9092";
	// eventJointMessage 프로듀서 및 템플릿
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
	// eventJointMessage 컨슈머
	@Bean
	public ConsumerFactory<String, EventJoinMessage> eventJoinMessageConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "summer-event-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonDeserializer.class);

		props.put("spring.json.value.default.type", "com.java.backend.domain.promotion.dto.EventJoinMessage");
		props.put("spring.json.trusted.packages", "com.java.backend.domain.promotion.dto");

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EventJoinMessage> eventJoinMessageListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EventJoinMessage> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(eventJoinMessageConsumerFactory());
		return factory;
	}

// eventJointMessage 컨슈머 ====> Outbox Message 컨슈머
// MeMo: 리스너에서 명시적으로 팩토리를 지정해주지 않아도
	// ConcurrentKafkaListenerContainerFactory가 1개라도 정의 되어 있다면, 기본설정(application.properties)을 덮어씌우는 현상때문에 추가

	@Bean
	public ConsumerFactory<String, String> outboxMessageConsumerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "main_server");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean ConcurrentKafkaListenerContainerFactory<String,String> outboxMessageListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(outboxMessageConsumerFactory());
		return factory;
	}
// eventResultMessage 프로듀서 및 템플릿
	@Bean
	public ProducerFactory<String, EventResultMessage> eventResultMessageProducerFactory(){
		Map<String,Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);
		config.put("spring.json.type.mapping", "EventResultMessage:com.java.backend.domain.promotion.dto.EventResultMessage");
		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, EventResultMessage> eventResultMessageKafkaTemplate() {
		return new KafkaTemplate<>(eventResultMessageProducerFactory());
	}
}
