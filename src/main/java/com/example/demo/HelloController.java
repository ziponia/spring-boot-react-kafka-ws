package com.example.demo;

import com.example.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/api")
    public String hello() throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, Object>> resultSend = messageService.send();
        System.out.println(resultSend.get().getProducerRecord());
        System.out.println(resultSend.get().getRecordMetadata());
        return "success";
    }
}
