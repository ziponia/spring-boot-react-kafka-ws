package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public ListenableFuture<SendResult<String, Object>> send() {
        return kafkaTemplate.send("test-topic", "hello kafka data");
    }

    @KafkaListener(topics = "test-topic")
    private void receiver(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("topic - [{}] message - '{}'", "/topic/" + topic, message);
        messagingTemplate.convertAndSend("/topic/" + topic, message);
    }
}
