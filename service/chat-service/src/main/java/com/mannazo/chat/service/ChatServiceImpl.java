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

    public Flux<Message> getMsg(String sender, String receiver){
        return reactiveMessageRepository.mFindBySender(sender, receiver);
    }

    @Override
    public Flux<Message> findByRoomId(String roomId) {
        return reactiveMessageRepository.mfindByRoomId(roomId);
    }

    @Override
    public Mono<Message> saveMsg(Message message) {
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

                    // 채팅방의 마지막 메시지 조회
                    Optional<Message> lastMessageOpt = messageRepository.findTopByRoomIdOrderByCreatedAtDesc(chatRoom.getRoomId());

                    // 마지막 메시지를 ChatRoomResponseDTO에 설정
                    lastMessageOpt.ifPresent(lastMessage -> {
                        chatRoomResponseDTO.setLastMessage(lastMessage.getMsg());
                        chatRoomResponseDTO.setLastMessageTime(lastMessage.getCreatedAt());
                    });

                    return chatRoomResponseDTO;
                })
                .collect(Collectors.toList());

        return chatRoomResponseDTOList;
    }

    @Override
    public ChatRoomEntity saveChatRoom(ChatRoomRequestDTO chatRoomDTO) {
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setUser1Id(chatRoomDTO.getUser1Id());
        chatRoom.setUser2Id(chatRoomDTO.getUser2Id());
        chatRoom.setCreatedAt(LocalDateTime.now());
        return chatRoomRepository.save(chatRoom);
    }
}
