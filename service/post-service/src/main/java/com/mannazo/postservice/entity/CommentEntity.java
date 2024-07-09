package com.mannazo.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_at")
    private Timestamp createAt;
}
