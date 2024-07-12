package com.mannazo.chat.service;

import com.mannazo.chat.entity.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {
    Flux<Message> getMsg(String sender, String receiver);
    Flux<Message> findByRoomId(String roomId);
    Mono<Message> saveMsg(Message message);
}
