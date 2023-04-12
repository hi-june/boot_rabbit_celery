package com.example.demo.domain.producer.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Message {
    private String type;
    private String title;
    private String message;
}
