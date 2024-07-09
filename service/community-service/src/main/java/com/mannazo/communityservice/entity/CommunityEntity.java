package com.mannazo.communityservice.entity;

import com.mannazo.communityservice.client.dto.UserResponseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
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

    @Transient
    private UserResponseDTO user;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "status")
    private String status;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;

    @Column(name = "view_count")
    private Long viewCount;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}
