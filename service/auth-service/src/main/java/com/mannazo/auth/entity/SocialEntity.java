package com.mannazo.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "social")
@Getter
public class SocialEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private UUID userid;
    @Column(name = "sosial_id", nullable = false, unique = true)
    private String sosialId;
}
