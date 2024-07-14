package com.mannazo.chat.repository;

import com.mannazo.chat.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {
    @Query("SELECT cr FROM ChatRoomEntity cr WHERE cr.user1Id = :userId OR cr.user2Id = :userId")
    List<ChatRoomEntity> findAllByUserId(@Param("userId") String userId);
}
