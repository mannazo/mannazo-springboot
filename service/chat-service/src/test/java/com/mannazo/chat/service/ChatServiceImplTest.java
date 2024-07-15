package com.mannazo.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ChatServiceImplTest {
    @Autowired
    private ChatService chatService;

    @Test
    void findAllChatRoomByUserId() {

        log.info(chatService.findAllChatRoomByUserId("e0cd2bc1-39b5-485c-8ac4-f00d398cbe20").toString());
    }
}