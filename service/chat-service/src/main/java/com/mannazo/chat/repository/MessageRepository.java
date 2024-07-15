package com.mannazo.chat.repository;

import com.mannazo.chat.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    @Query(value = "{ 'roomId' : ?0 }", sort = "{ 'createdAt' : -1 }")
    Optional<Message> findTopByRoomIdOrderByCreatedAtDesc(String roomId);
}