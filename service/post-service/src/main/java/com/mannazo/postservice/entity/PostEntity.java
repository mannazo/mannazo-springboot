package com.mannazo.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Post")
public class PostEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false, unique = true)
    private UUID postId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "travel_nationality", nullable = true)
    private String travelNationality;

    @Column(name = "travel_city")
    private String travelCity;

    @Column(name = "travel_start_date")
    private LocalDate travelStartDate;

    @Column(name = "travel_end_date")
    private LocalDate travelEndDate;

    @Column(name = "travel_status")
    @Enumerated(EnumType.STRING)
    private TravelStatus travelStatus;

    @Column(name = "preferred_gender", nullable = true)
    @Enumerated(EnumType.STRING)
    private PreferredGender preferredGender;

    @Column(name = "travel_style", columnDefinition = "TEXT", nullable = true)
    private String travelStyle;

    @Column(name = "travel_purpose", columnDefinition = "TEXT", nullable = true)
    private String travelPurpose;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}
