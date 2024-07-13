package com.mannazo.chat.dto;

import com.mannazo.chat.client.user.UserResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class ChatRoomResponseDTO {

    private UserResponseDTO user1;
    private UserResponseDTO user2;
    private String chatRoomId;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private LocalDateTime createdAt;
}
