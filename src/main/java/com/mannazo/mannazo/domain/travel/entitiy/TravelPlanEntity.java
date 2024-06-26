package com.mannazo.mannazo.domain.travel.entitiy;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "TravelPlan")
public class TravelPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_id", nullable = false, unique = true)
    private UUID tripId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "destination", nullable = false, length = 255)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;

    @Column(name = "travel_style", columnDefinition = "TEXT")
    private String travelStyle;
}
