package com.mannazo.mannazo.domain.travel.repository;

import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TravelPlanRepository extends JpaRepository<TravelPlanEntity, UUID> {

}
