package com.mannazo.chat.controller;


import com.mannazo.chat.entity.Message;
import com.mannazo.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    // 메시지 보내기
    @PostMapping("/chat")
    public Mono<Message> sendMsg(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());

        return chatService.saveMsg(message);
    }

    //
    @GetMapping(value = "/chat/roomId/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> findByRoomId(@PathVariable("roomId") String roomId) {
        return chatService.findByRoomId(roomId)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
