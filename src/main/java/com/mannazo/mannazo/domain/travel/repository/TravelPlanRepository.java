package com.mannazo.mannazo.domain.travel.repository;

import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TravelPlanRepository extends JpaRepository<TravelPlanEntity, UUID> {

    List<TravelPlanEntity> findByUserId(UUID userId);

    @Query("SELECT tp FROM TravelPlanEntity tp WHERE tp.userId IN :userIds")
    List<TravelPlanEntity> findByUserIds(@Param("userIds") List<UUID> userIds);

    @Query("SELECT tp FROM TravelPlanEntity tp WHERE :date BETWEEN tp.startDate AND tp.endDate")
    List<TravelPlanEntity> findByDateBetween(@Param("date") LocalDate date);
}
