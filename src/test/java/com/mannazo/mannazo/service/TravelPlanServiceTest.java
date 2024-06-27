package com.mannazo.mannazo.service;

import com.mannazo.mannazo.domain.account.dto.request.UserRequestDTO;
import com.mannazo.mannazo.domain.account.dto.response.UserResponseDTO;
import com.mannazo.mannazo.domain.account.service.UserService;
import com.mannazo.mannazo.domain.travel.dto.request.TravelPlanRequestDTO;
import com.mannazo.mannazo.domain.travel.dto.response.TravelPlanResponseDTO;
import com.mannazo.mannazo.domain.travel.entitiy.TravelPlanEntity;
import com.mannazo.mannazo.domain.travel.repository.TravelPlanRepository;
import com.mannazo.mannazo.domain.travel.service.TravelPlanService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Period;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class TravelPlanServiceTest {

    @Autowired
    private TravelPlanService travelPlanService;

    @Autowired
    private UserService userService;

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @Test
    public void 여행계획_테스트(){
        // Given
        UUID tripId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String destination = "서울";

        LocalDate startDate = LocalDate.of(2024, 7, 19);
        LocalDate endDate = startDate.plusDays(7); // 일주일 후Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp createAt = new Timestamp(System.currentTimeMillis());
        String travelStyles = "음식, 문화";

        TravelPlanRequestDTO travelInfo = TravelPlanRequestDTO.builder()
                .tripId(tripId)
                .userId(userId)
                .destination(destination)
                .startDate(startDate)
                .endDate(endDate)
                .createAt(createAt)
                .travelStyle(travelStyles)
                .build();
        travelPlanService.registerTravelPlan(travelInfo);
    }

    @Test
    public void 여행계획_조회테스트() {
        UUID tripId = UUID.fromString("6f201bc7-aaeb-46b9-b0a3-f6ed8affba51");
        log.info(travelPlanService.getTravelPlan(tripId).toString());
    }

    @Test
    public void 여행계획_수정테스트() {
        //Given
        UUID tripId = UUID.fromString("ea312454-edaf-4d9d-923e-287ed1199bb7");

        TravelPlanRequestDTO dto = TravelPlanRequestDTO.builder()
                .tripId(tripId)
                .userId(UUID.randomUUID())
                .destination("목포")
                .startDate(LocalDate.of(2024, 7, 19))
                .endDate(LocalDate.of(2024, 7, 27))
                .createAt(new Timestamp(System.currentTimeMillis()))
                .travelStyle("고궁")
                .build();


        //수정내용
        TravelPlanEntity updatedTravelPlanEntity = travelPlanRepository.save(dto.toEntity());

        //Then
        log.info("수정완료" +updatedTravelPlanEntity.toString());
    }

    @Test
    public void 여행계획_삭제테스트() {
        //Given
        UUID tripId = UUID.fromString("6f201bc7-aaeb-46b9-b0a3-f6ed8affba51");
        travelPlanService.deleteTravelPlan(tripId);
    }

    //테스트용 : 유저20개 생성, 여행계획 20개 무작위 생성
    @Test
    public void 유저_및_여행계획_테스트() {
        // Given
        List<UserRequestDTO> userList = new ArrayList<>();
        List<UUID> userIds = new ArrayList<>();

        Random random = new Random();

        String[] names = {"박정현", "김철수", "이영희", "최지훈", "정민수", "박영수", "김민준", "이현우", "최서윤", "정지호",
                "박서연", "김지은", "이민지", "최현수", "정수민", "박주원", "김다연", "이준호", "최지원", "정다빈"};
        String[] nicknames = {"nick1", "nick2", "nick3", "nick4", "nick5",
                "nick6", "nick7", "nick8", "nick9", "nick10",
                "nick11", "nick12", "nick13", "nick14", "nick15",
                "nick16", "nick17", "nick18", "nick19", "nick20"};
        String[] nationalities = {"Republic of Korea", "USA", "Japan", "China", "Germany",
                "France", "UK", "Canada", "Australia", "India"};
        String[] languages = {"Kor", "Eng", "Jpn", "Chn", "Ger", "Fra", "Spa", "Ita", "Rus", "Hin"};
        String[] cities = {"Seoul", "Busan", "Incheon", "Daegu", "Daejeon", "Gwangju", "Suwon", "Ulsan", "Changwon", "Sejong"};
        String[] genders = {"남자", "여자"};
        String[] mbtis = {"ISTJ", "INFJ", "INTP", "ENFP", "ENTJ", "ISFP", "ESFJ", "ESTP", "INFP", "ENTP"};
        String[] interests = {"Reading", "Coding", "Music", "Sports", "Travel",
                "Movies", "Art", "Dancing", "Gaming", "Cooking"};

        // 유저 생성 및 저장
        for (int i = 0; i < 20; i++) {
            String email = "test" + (i + 1) + "@test.com"; // 각 이메일을 고유하게 설정
            String name = names[i];
            String nickname = nicknames[i];
            String nationality = nationalities[random.nextInt(nationalities.length)];
            String language = languages[random.nextInt(languages.length)];
            String city = cities[random.nextInt(cities.length)];
            String gender = genders[random.nextInt(genders.length)];
            String mbti = mbtis[random.nextInt(mbtis.length)];

            List<String> selectedInterests = new ArrayList<>();
            selectedInterests.add(interests[random.nextInt(interests.length)]);
            selectedInterests.add(interests[random.nextInt(interests.length)]);

            // 나이 계산을 위해 현재 날짜와 비교하여 생일 설정
            LocalDate birthday = LocalDate.of(1980 + random.nextInt(30), random.nextInt(12) + 1, random.nextInt(28) + 1);
            LocalDate today = LocalDate.now();
            int age = Period.between(birthday, today).getYears();

            String profilePhoto = "photoLink" + i;
            String introduction = "Introduction " + i;

            UserRequestDTO user = UserRequestDTO.builder()
                    .email(email)
                    .name(name)
                    .nickname(nickname)
                    .nationality(nationality)
                    .language(language)
                    .profilePhoto(profilePhoto)
                    .introduction(introduction)
                    .city(city)
                    .gender(gender)
                    .mbti(mbti)
                    .interests(selectedInterests)
                    .birthday(birthday)
                    .age(age)
                    .build();

            UserResponseDTO savedUser = userService.registerUser(user);
            userIds.add(savedUser.getUserId());
        }

        // 여행 계획 생성 및 저장
        String[] destinations = {"Seoul", "Busan", "Jeju", "Incheon", "Gangwon", "Daegu", "Gwangju", "Daejeon", "Ulsan", "Gyeongju"};
        String[] travelStyles = {"Sightseeing", "Food", "Culture", "History", "Nature", "Adventure", "Relaxation", "Shopping", "Nightlife", "Sports"};

        for (int i = 0; i < 20; i++) {
            UUID userId = userIds.get(random.nextInt(userIds.size()));
            String destination = destinations[random.nextInt(destinations.length)];
            LocalDate startDate = LocalDate.of(2024, random.nextInt(12) + 1, random.nextInt(28) + 1);
            LocalDate endDate = startDate.plusDays(random.nextInt(14) + 1);
            Timestamp createAt = new Timestamp(System.currentTimeMillis());
            String travelStyle = travelStyles[random.nextInt(travelStyles.length)];

            TravelPlanRequestDTO travelPlan = TravelPlanRequestDTO.builder()
                    .userId(userId)
                    .destination(destination)
                    .startDate(startDate)
                    .endDate(endDate)
                    .createAt(createAt)
                    .travelStyle(travelStyle)
                    .build();

            travelPlanService.registerTravelPlan(travelPlan);
        }
    }

    @Test
    public void testPaging() {
        // Given
        int pageNumber = 1; // 페이지 번호 (1부터 시작)
        int pageSize = 5;   // 페이지당 아이템 수
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createAt"));


        // When
        Page<TravelPlanResponseDTO> result = travelPlanService.paging(pageable);

        // Log results
        log.info("Total elements: " + result.getTotalElements());
        log.info("Current page number: " + result.getNumber());
        log.info("Page size: " + result.getSize());
        log.info("Number of elements in current page: " + result.getNumberOfElements());
        log.info("Total pages: " + result.getTotalPages());
        log.info("Is first page: " + result.isFirst());
        log.info("Is last page: " + result.isLast());
        log.info("Has previous page: " + result.hasPrevious());
        log.info("Has next page: " + result.hasNext());
        log.info("Content:");
        result.getContent().forEach(dto -> log.info(dto.toString()));
    }

    //필터링 테스트: language
    @Test
    public void findByLanguage() {
        // Given
        String language = "Spa";

        // When
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByLanguage(language);

        // Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>"+travelPlans);
    }

    //필터링 테스트: gender
    @Test
    public void testFindByGender() {
        //Given
        String gender = "남자";

        //When
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByGender(gender);

        //Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>");
        for (TravelPlanResponseDTO travelPlan : travelPlans) {
            log.info("UserId: {}", travelPlan.getUserId());
        }
    }

    //필터링 테스트: interests
    @Test
    public void testFindByInterests() {
        //Given
        String interests = "Movies";

        //When
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByInterests(interests);

        //Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>");
        for (TravelPlanResponseDTO travelPlan : travelPlans) {
            log.info("UserId: {}", travelPlan.getUserId());
        }
    }

    //필터링 테스트: travelStyle
    @Test
    public void testFindByTravelStyle() {
        //Given
        String travelStyle = "Culture";

        //When
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByTravelStyle(travelStyle);

        //Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>");
        for (TravelPlanResponseDTO travelPlan : travelPlans) {
            log.info("UserId: {}", travelPlan.getUserId());
        }
    }

    //필터링 테스트: userId
    @Test
    public void testFindByUserId() {
        // Given
        UUID userId = UUID.fromString("e5a5bdd5-f430-45cf-ab8c-4ccc85866413");

        //When
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByUserId(userId);

        //Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>");
        for (TravelPlanResponseDTO travelPlan : travelPlans) {
            log.info("UserId: {}", travelPlan);
        }
    }

    //필터링 테스트: destination
    @Test
    public void testFindByDestination() {
        //Given
        String destination = "Jeju";

        //When
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByDestination(destination);

        //Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>");
        for (TravelPlanResponseDTO travelPlan : travelPlans) {
            log.info("UserId: {}", travelPlan.getUserId());
        }
    }

    //필터링 테스트: 시작날짜 - 끝날짜 사이
    @Test
    public void testFindByBetween() {
        //Given
        LocalDate date = LocalDate.of(2024, 10, 18);

        //when
        List<TravelPlanResponseDTO> travelPlans = travelPlanService.findByDateBetween(date);

        //Then
        assertThat(travelPlans).isNotEmpty();
        log.info("조회======>");
        for (TravelPlanResponseDTO travelPlan : travelPlans){
            log.info("UserId: {}", travelPlan.getUserId());
        }
    }
}

