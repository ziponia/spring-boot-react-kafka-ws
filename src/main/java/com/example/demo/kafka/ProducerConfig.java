package com.example.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Slf4j
@Configuration
public class ProducerConfig {

    @Autowired
    private KafkaProperties properties;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public DefaultKafkaProducerFactory<String, Object> producerFactory() {

        log.info("[Kafka Client] Install Producer: {}", properties.buildProducerProperties());
        for (String key : properties.buildProducerProperties().keySet()) {
            log.info("[Kafka Producer property] '{}' - '{}'",  key,  properties.buildProducerProperties());
        }
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties(), null, new JsonSerializer<>());
    }
}
