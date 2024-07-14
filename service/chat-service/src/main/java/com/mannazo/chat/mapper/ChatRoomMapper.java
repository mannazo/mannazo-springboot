package com.mannazo.chat.mapper;

import com.mannazo.chat.client.user.UserFeignClient;
import com.mannazo.chat.client.user.UserResponseDTO;
import com.mannazo.chat.dto.ChatRoomResponseDTO;
import com.mannazo.chat.entity.ChatRoomEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ChatRoomMapper {

    @Autowired
    private UserFeignClient userFeignClient;

    @Mappings({
            @Mapping(target = "user1", source = "entity.user1Id", qualifiedByName = "userIdToUserDTO"),
            @Mapping(target = "user2", source = "entity.user2Id", qualifiedByName = "userIdToUserDTO"),
            @Mapping(target = "lastMessage", ignore = true),
            @Mapping(target = "chatRoomId", source = "roomId"),
            @Mapping(target = "lastMessageTime", ignore = true)
    })
    public abstract ChatRoomResponseDTO toDto(ChatRoomEntity entity);

//    public abstract ChatRoomEntity toEntity(ChatRoomResponseDTO dto);

    @Named("userIdToUserDTO")
    public UserResponseDTO userIdToUserDTO(UUID userId) {
        return userFeignClient.getUser(userId).get();
    }
}
