package com.mannazo.communityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Likes")
public class LikeEntity {

    @Id
    @GeneratedValue
    @Column(name = "like_id", nullable = false, unique = true)
    private UUID likeId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private CommunityEntity community;
}
