package com.mannazo.chat.service;

import com.mannazo.chat.dto.ChatRoomRequestDTO;
import com.mannazo.chat.dto.ChatRoomResponseDTO;
import com.mannazo.chat.entity.ChatRoomEntity;
import com.mannazo.chat.entity.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatService {
    Flux<Message> findByRoomId(String roomId);
    Mono<Message> sendMsg(Message message);

    List<ChatRoomResponseDTO> findAllChatRoomByUserId(String userId);
    ChatRoomResponseDTO saveChatRoom(ChatRoomRequestDTO chatRoomDTO);
}
