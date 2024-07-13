package com.mannazo.chat.service;

import com.mannazo.chat.client.user.UserFeignClient;
import com.mannazo.chat.dto.ChatRoomRequestDTO;
import com.mannazo.chat.dto.ChatRoomResponseDTO;
import com.mannazo.chat.entity.ChatRoomEntity;
import com.mannazo.chat.entity.Message;
import com.mannazo.chat.mapper.ChatRoomMapper;
import com.mannazo.chat.repository.ChatRoomRepository;
import com.mannazo.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;

    private final UserFeignClient userFeignClient;

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

    @Override
    public List<ChatRoomResponseDTO> findAllChatRoomByUserId(String userId) {
        // 받은 채팅방 리스트를 스트림 -> ChatRoomResponseDTO
        List<ChatRoomEntity> chatRooms = chatRoomRepository.findAllByUserId(userId);

        List<ChatRoomResponseDTO> chatRoomResponseDTOList = chatRooms.stream()
                .map(chatRoom -> {
                    ChatRoomResponseDTO chatRoomResponseDTO = chatRoomMapper.toDto(chatRoom);
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
