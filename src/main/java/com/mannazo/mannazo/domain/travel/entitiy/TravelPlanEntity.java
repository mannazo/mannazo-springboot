package com.mannazo.mannazo.domain.travel.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TravelPlan")
@AllArgsConstructor @NoArgsConstructor
public class TravelPlanEntity {

    @Id
    @Column(name = "trip_id", columnDefinition = "BINARY(16)")
    private UUID tripId;

    @Column(name = "user_id", columnDefinition = "CHAR(36)", nullable = false)
    private String userId;

    @Column(name = "destination", nullable = false, length = 255)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "interests", columnDefinition = "TEXT")
    private String interests;
}
