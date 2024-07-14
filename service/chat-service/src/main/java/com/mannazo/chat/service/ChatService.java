package com.mannazo.chat.service;

import com.mannazo.chat.dto.ChatRoomRequestDTO;
import com.mannazo.chat.dto.ChatRoomResponseDTO;
import com.mannazo.chat.entity.ChatRoomEntity;
import com.mannazo.chat.entity.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatService {
    Flux<Message> getMsg(String sender, String receiver);
    Flux<Message> findByRoomId(String roomId);
    Mono<Message> saveMsg(Message message);

    List<ChatRoomResponseDTO> findAllChatRoomByUserId(String userId);
    ChatRoomEntity saveChatRoom(ChatRoomRequestDTO chatRoomDTO);
}
