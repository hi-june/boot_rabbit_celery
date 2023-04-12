package com.example.demo.domain.producer.controller;

import com.example.demo.domain.producer.message.Message;
import com.example.demo.domain.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestBody Message message) {
        producerService.sendMessage(message);
        return "published!";
    }
}
