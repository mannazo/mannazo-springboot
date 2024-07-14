package com.mannazo.chat.repository;

import com.mannazo.chat.entity.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    @Tailable
    @Query("{ 'sender' : ?0, 'receiver' : ?1 }")
    Flux<Message> mFindBySender(String sender, String receiver);

    @Tailable
    @Query("{ 'roomId' : ?0 }")
    Flux<Message> mfindByRoomId(String roomId);
}