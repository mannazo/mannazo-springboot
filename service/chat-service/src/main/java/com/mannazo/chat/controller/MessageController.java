package com.mannazo.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class MessageController {
//    private final KafkaMessageService kafkaMessageService;
//
//    @MessageMapping("/message")
//    public void sendMessage(MessageDto message) throws ExecutionException, InterruptedException {
//        //메세지 서비스 로직
//    }
}