package com.mannazo.mannazo.domain.travel.entitiy;

import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.global.util.EnumUtils;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "travel_country", nullable = false, length = 255)
    private String travelCountry;

    @Column(name = "travel_city", nullable = false, length = 255)
    private String travelCity;

    @Column(name = "address_detail", nullable = true, length = 255)
    private String addressDetail;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "preferred_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumUtils.PreferredGender preferredGender;

    @Column(name = "travel_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumUtils.TravelStatus travelStatus;

    @Column(name = "create_at", nullable = false)
    private Timestamp createAt;

    @Column(name = "travel_purpose", columnDefinition = "TEXT")
    private String travelPurpose;

    @PrePersist
    protected void onCreate() {
        this.createAt = Timestamp.valueOf(LocalDateTime.now());
        System.out.println("onCreate called, createAt: " + this.createAt);
    }
}
