package com.example.demo.domain.producer.service;

import com.example.demo.domain.producer.message.Message;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);
    private final RabbitTemplate rabbitTemplate;    // RabbitTemplate을 통해 Exchange에 메세지를 보내도록 설정

    public void sendMessage(Message message) {
        // rabbitTemplate.convertAndSend("hello.exchange", "hello.key", message);
        rabbitTemplate.convertAndSend("default", "input.key", message);
    }
}
