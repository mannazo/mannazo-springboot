package com.mannazo.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Document(collection = "messages")
@AllArgsConstructor
public class Message {

    @Id
    private String id;

    private String senderId;    // 보내는 유저
    private String roomId;      // 채팅방 아이디
    private String msg;

    private LocalDateTime createdAt;
}