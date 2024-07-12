package com.mannazo.chat.service;

import com.mannazo.chat.entity.Message;
import com.mannazo.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final MessageRepository messageRepository;

    public Flux<Message> getMsg(String sender, String receiver){
        return messageRepository.mFindBySender(sender, receiver);
    }

    @Override
    public Flux<Message> findByRoomId(String roomId) {
        return messageRepository.mfindByRoomId(roomId);
    }

    @Override
    public Mono<Message> saveMsg(Message message) {
        return messageRepository.save(message);
    }


}
