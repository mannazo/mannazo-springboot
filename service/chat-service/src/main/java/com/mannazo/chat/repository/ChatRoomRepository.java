package com.mannazo.chat.repository;

import com.mannazo.chat.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {
    // 사용자 아이디를 통해 채팅방 리스트를 모두 반환
    @Query("SELECT cr FROM ChatRoomEntity cr WHERE cr.user1Id = :userId OR cr.user2Id = :userId")
    List<ChatRoomEntity> findAllByUserId(@Param("userId") String userId);
    
    // 사용자1, 2가 사용하는 채팅방 조회
    @Query("SELECT cr FROM ChatRoomEntity cr WHERE (cr.user1Id = :user1Id AND cr.user2Id = :user2Id) OR (cr.user1Id = :user2Id AND cr.user2Id = :user1Id)")
    Optional<ChatRoomEntity> findByUserIds(@Param("user1Id") String user1Id, @Param("user2Id") String user2Id);

}
