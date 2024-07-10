package com.mannazo.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "social")
@Getter
@Setter
public class SocialEntity {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userid;
    @Column(name = "social_id", nullable = false, unique = true)
    private String socialId;
}
