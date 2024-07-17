package com.mannazo.chat.service;

import com.mannazo.chat.client.user.UserFeignClient;
import com.mannazo.chat.dto.ChatRoomRequestDTO;
import com.mannazo.chat.dto.ChatRoomResponseDTO;
import com.mannazo.chat.entity.ChatRoomEntity;
import com.mannazo.chat.entity.Message;
import com.mannazo.chat.mapper.ChatRoomMapper;
import com.mannazo.chat.repository.ChatRoomRepository;
import com.mannazo.chat.repository.MessageRepository;
import com.mannazo.chat.repository.ReactiveMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ReactiveMessageRepository reactiveMessageRepository;
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;

    private final UserFeignClient userFeignClient;

    @Override
    public Flux<Message> findByRoomId(String roomId) {
        // 사용자 아이디를 받아 채팅방을 찾음
        return reactiveMessageRepository.mfindByRoomId(roomId);
    }

    @Override
    public Mono<Message> sendMsg(Message message) {
        // 사용자가 보낸 메시지를 DB에 저장
        return reactiveMessageRepository.save(message);
    }

    @Override
    public List<ChatRoomResponseDTO> findAllChatRoomByUserId(String userId) {
        // 받은 채팅방 리스트를 조회
        List<ChatRoomEntity> chatRooms = chatRoomRepository.findAllByUserId(userId);

        // 각 채팅방에 대한 ChatRoomResponseDTO를 매핑하여 리스트로 반환
        List<ChatRoomResponseDTO> chatRoomResponseDTOList = chatRooms.stream()
                .map(chatRoom -> {
                    // ChatRoomEntity를 ChatRoomResponseDTO로 변환
                    ChatRoomResponseDTO chatRoomResponseDTO = chatRoomMapper.toDto(chatRoom);

//                    // 채팅방의 마지막 메시지 조회
//                    Optional<Message> lastMessageOpt = messageRepository.findTopByRoomIdOrderByCreatedAtDesc(chatRoom.getRoomId());
//
//                    // 마지막 메시지를 ChatRoomResponseDTO에 설정
//                    lastMessageOpt.ifPresent(lastMessage -> {
//                        chatRoomResponseDTO.setLastMessage(lastMessage.getMsg());
//                        chatRoomResponseDTO.setLastMessageTime(lastMessage.getCreatedAt());
//                    });

                    return chatRoomResponseDTO;
                })
                .collect(Collectors.toList());

        return chatRoomResponseDTOList;
    }

    @Override
    public ChatRoomResponseDTO saveChatRoom(ChatRoomRequestDTO chatRoomDTO) {

        // 사용자1Id와 사용자2Id로 채팅방을 찾습니다.
        Optional<ChatRoomEntity> existingChatRoom = chatRoomRepository.findByUserIds(chatRoomDTO.getUser1Id(), chatRoomDTO.getUser2Id());

        // 채팅방이 이미 존재하는 경우 해당 채팅방을 반환합니다.
        if (existingChatRoom.isPresent()) {
            return chatRoomMapper.toDto(existingChatRoom.get());
        } else {
            // 채팅방이 존재하지 않는 경우 새로운 채팅방을 생성하고 저장합니다.
            ChatRoomEntity newChatRoom = new ChatRoomEntity();
            newChatRoom.setUser1Id(chatRoomDTO.getUser1Id());
            newChatRoom.setUser2Id(chatRoomDTO.getUser2Id());
            newChatRoom.setCreatedAt(LocalDateTime.now());

            // 채팅방 저장 후 반환
            return chatRoomMapper.toDto(chatRoomRepository.save(newChatRoom));
        }
    }
}
