package com.mannazo.postservice.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class PostEntity {

    @Id
    @GeneratedValue
    @Column(name = "trip_id", nullable = false, unique = true)
    private UUID tripId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "t_nationality")
    private String tNationality;

    @Column(name = "t_city")
    private String tCity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "w_gender")
    private WGender wGender;

    @Column(name = "t_style")
    private String tStyle;

    @Column(name = "t_goal")
    private String tGoal;

}
