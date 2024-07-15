package com.mannazo.communityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Community")
public class CommunityEntity {

    @Id
    @GeneratedValue
    @Column(name = "community_id", nullable = false, unique = true)
    private UUID communityId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "like_count", nullable = false)
    private int likeCount = 0;


    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likes;

    @PrePersist
    protected void onCreate() {
        if (this.createAt == null) {
            this.createAt = LocalDateTime.now(); // 현재 시간으로 기본값 설정
        }
        if (this.viewCount == null) {
            this.viewCount = 0L;
        }
        if (this.lastUpdated == null) {
            this.lastUpdated = LocalDateTime.now(); // 현재 시간으로 기본값 설정
        }
    }
}
