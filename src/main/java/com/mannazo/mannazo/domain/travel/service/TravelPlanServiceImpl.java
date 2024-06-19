package com.mannazo.mannazo.domain.travel.service;

import com.mannazo.mannazo.domain.travel.repository.TravelPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelPlanServiceImpl {

    private final TravelPlanRepository travelPlanRepository;

}
