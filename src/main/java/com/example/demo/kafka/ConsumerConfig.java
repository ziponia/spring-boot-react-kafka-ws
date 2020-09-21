package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Slf4j
@Configuration
public class ConsumerConfig {

    @Autowired
    private KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        log.info("[Kafka Client] Install Consumer: {}", properties.getConsumer().buildProperties());
        return new DefaultKafkaConsumerFactory<>(properties.getConsumer().buildProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
