package com.mannazo.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ChatRoom")
@Setter
@Getter
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roomId;
    private String user1Id;
    private String user2Id;
//    private String lastMessageId;

    private LocalDateTime createdAt;

}
