package com.mannazo.chat.controller;


import com.mannazo.chat.dto.ChatRoomRequestDTO;
import com.mannazo.chat.dto.ChatRoomResponseDTO;
import com.mannazo.chat.entity.ChatRoomEntity;
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
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/")
    public String Chat() {
        return "Hello Chat-Service";
    }

    // 메시지 보내기
    @PostMapping("/")
    public Mono<Message> sendMsg(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return chatService.saveMsg(message);
    }

    // 채팅방 입장
    @GetMapping(value = "/roomId/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> findByRoomId(@PathVariable("roomId") String roomId) {
        return chatService.findByRoomId(roomId)
                .subscribeOn(Schedulers.boundedElastic());
    }
    
    // 채팅방 목록 확인
    @GetMapping("/room/userId/{userId}")
    public List<ChatRoomResponseDTO> findAllByUserId(@PathVariable("userId") String userId) {
        return chatService.findAllChatRoomByUserId(userId);
    }

    // 채팅방 생성
    @PostMapping("/room")
    public ChatRoomEntity createRoom(@RequestBody ChatRoomRequestDTO chatRoomDTO) {
        return chatService.saveChatRoom(chatRoomDTO);
    }

}
