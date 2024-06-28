package com.mannazo.mannazo.domain.travel.service;

import com.mannazo.mannazo.domain.account.entity.UserEntity;
import com.mannazo.mannazo.domain.account.repository.UserRepository;
import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.domain.travel.repository.TravelPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelPlanServiceImpl implements TravelPlanService {

    private final TravelPlanRepository travelPlanRepository;
    private final UserRepository userRepository;

    @Override
    public TravelPlanResponseDTO getTravelPlan(UUID tripId) {
        TravelPlanEntity travelInfo = travelPlanRepository.findById(tripId).orElseThrow(() -> new NoSuchElementException("Travel plan not found"));
        return TravelPlanResponseDTO.fromEntity(travelInfo);
    }

    @Override
    public TravelPlanResponseDTO registerTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO) {

        // TravelPlanRequestDTO에서 TravelPlanEntity로 변환
        TravelPlanEntity travelPlan = travelPlanRequestDTO.toEntity();

        // 엔티티를 데이터베이스에 저장
        travelPlan = travelPlanRepository.save(travelPlan);

        // 저장된 엔티티를 DTO로 변환하여 반환
        return TravelPlanResponseDTO.fromEntity(travelPlan);
    }

    @Override
    public TravelPlanResponseDTO updateTravelPlan(TravelPlanRequestDTO travelPlanRequestDTO) {
        TravelPlanEntity updatedTravelPlan = travelPlanRepository.save(travelPlanRequestDTO.toEntity());
        return TravelPlanResponseDTO.fromEntity(updatedTravelPlan);
    }

    @Override
    public TravelPlanResponseDTO.Delete deleteTravelPlan(UUID tripId) {
        travelPlanRepository.deleteById(tripId);
        return TravelPlanResponseDTO.Delete.builder()
                .tripId(tripId)
                .messages("해당 게시물이 삭제되었습니다.")
                .build();
    }

    @Override
    public List<TravelPlanResponseDTO> findAll() {
        List<TravelPlanEntity> travelEntityList = travelPlanRepository.findAll();
        // 엔티티 리스트를 DTO 리스트로 변환합니다.
        return travelEntityList.stream()
                .map(TravelPlanResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TravelPlanResponseDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 페이지 번호는 0부터 시작하므로 그대로 사용
        int pageLimit = 5; // 한페이지에 보여줄 글 갯수
        // 한 페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬

        // PageRequest.of()를 사용하여 Pageable 객체를 생성할 때에는 페이지 번호를 그대로 사용
        Page<TravelPlanEntity> travelPlanEntities =
                travelPlanRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "tripId")));

        log.info("boardEntities.getContent() = " + travelPlanEntities.getContent()); // 요청 페이지에 해당하는 글
        log.info("boardEntities.getTotalElements() = " + travelPlanEntities.getTotalElements()); // 전체 글갯수
        log.info("boardEntities.getNumber() = " + travelPlanEntities.getNumber()); // DB로 요청한 페이지 번호
        log.info("boardEntities.getTotalPages() = " + travelPlanEntities.getTotalPages()); // 전체 페이지 갯수
        log.info("boardEntities.getSize() = " + travelPlanEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        log.info("boardEntities.hasPrevious() = " + travelPlanEntities.hasPrevious()); // 이전 페이지 존재 여부
        log.info("boardEntities.isFirst() = " + travelPlanEntities.isFirst()); // 첫 페이지 여부
        log.info("boardEntities.isLast() = " + travelPlanEntities.isLast()); // 마지막 페이지 여부

        //목록 : tripid, 목적지, 시작시간, 끝시간, 작성시간
        Page<TravelPlanResponseDTO> travelPlanResponseDTOPage = travelPlanEntities.map(travel ->
                TravelPlanResponseDTO.builder()
                        .tripId(travel.getTripId())
                        .userId(travel.getUserId())
                        .travelCountry(travel.getTravelCountry())
                        .travelCity(travel.getTravelCity())
                        .endDate(travel.getEndDate())
                        .createAt(travel.getCreateAt())
                        .build());

        return travelPlanResponseDTOPage;
    }

    @Override
    public List<TravelPlanResponseDTO> findByLanguage(String language) {
        // 주어진 언어로 설정된 모든 사용자의 정보 조회
        List<UserEntity> users = userRepository.findByLanguage(language);

        //사용자의 userId를 통해 여행 계획을 조회
        List<UUID> userIds = users.stream()
                .map(UserEntity::getUserId)
                .collect(Collectors.toList());

        // userId 리스트로 여행 계획 조회
        List<TravelPlanEntity> travelPlans = travelPlanRepository.findByUserIds(userIds);

        // 여행 계획이 없는 경우 빈 리스트 반환
        if (travelPlans.isEmpty()) {
            log.info("해당하는 여행계획이 없습니다.");
            return Collections.emptyList();
        }

        //정상 목록 반환
        return travelPlans.stream()
                .map(TravelPlanResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelPlanResponseDTO> findByGender(String gender) {

        List<UserEntity> users = userRepository.findByGender(gender);

        List<UUID> userIds = users.stream()
                .map(UserEntity::getUserId)
                .collect(Collectors.toList());

        List<TravelPlanEntity> travelPlans = travelPlanRepository.findByUserIds(userIds);

        if (travelPlans.isEmpty()) {
            log.info("해당하는 여행계획이 없습니다.");
            return Collections.emptyList();
        }
        return travelPlans.stream()
                .map(TravelPlanResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelPlanResponseDTO> findByInterests(String interests) {

        List<UserEntity> users = userRepository.findByInterests(interests);

        List<UUID> userIds = users.stream()
                .map(UserEntity::getUserId)
                .collect(Collectors.toList());

        List<TravelPlanEntity> travelPlans = travelPlanRepository.findByUserIds(userIds);
        if (travelPlans.isEmpty()) {
            log.info("해당하는 여행계획이 없습니다.");
            return Collections.emptyList();
        }
        return travelPlans.stream()
                .map(TravelPlanResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelPlanResponseDTO> findByDateBetween(LocalDate date) {
        List<TravelPlanEntity> travelPlans = travelPlanRepository.findByDateBetween(date);
        if (travelPlans.isEmpty()){
            log.info("해당하는 여행계획이 없습니다.");
            return Collections.emptyList();
        }
        return travelPlans.stream()
                .map(TravelPlanResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public List<TravelPlanResponseDTO> findByUserId(UUID userId) {
        List<TravelPlanEntity> travelPlans = travelPlanRepository.findByUserId(userId);
        if (travelPlans.isEmpty()) {
            log.info("해당하는 여행계획이 없습니다.");
            return Collections.emptyList();
        }
        return travelPlans.stream()
                .map(TravelPlanResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

}