package com.mannazo.communityservice.entity;

import com.mannazo.communityservice.client.dto.UserResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Comment")
public class CommentEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id", nullable = false, unique = true)
    private UUID commentId;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_at")
    private Timestamp createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityEntity community;
}
