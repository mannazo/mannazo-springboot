package com.mannazo.mannazo.domain.travel.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Column(name = "trip_id", columnDefinition = "BINARY(16)")
    private UUID tripId;

    @Column(name = "user_id", columnDefinition = "CHAR(36)", nullable = false)
    private UUID userId;

    @Column(name = "destination", nullable = false, length = 255)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;

    @Column(name = "interests", columnDefinition = "TEXT")
    private String interests;
}
