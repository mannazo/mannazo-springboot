package com.mannazo.chat.repository;

import com.mannazo.chat.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@Slf4j
public class MessageRepositoryTest {

    @Autowired
    private ReactiveMongoRepository reactiveMongoRepository;

    @Test
    void mFindBySender() {
    }

    @Test
    void testMFindByRoomId() {
        String roomId = "60cdad56-b80c-4b90-adc9-c619b7fbde9c";


    }

    @Test
    public void testFindOneByRoomIdOrderByCreatedAtDesc() {

    }
}