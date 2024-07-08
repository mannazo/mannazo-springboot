package com.mannazo.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "social_to_user_id_mappings")
@Getter
public class SocialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sosial_id", nullable = false, unique = true)
    private String sosialId;
    @Column(name = "user_id", nullable = false)
    private UUID userid;
}
