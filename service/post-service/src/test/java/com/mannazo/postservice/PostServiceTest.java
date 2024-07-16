package com.mannazo.postservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mannazo.postservice.client.UserServiceClient;
import com.mannazo.postservice.dto.CommentRequestDTO;
import com.mannazo.postservice.dto.CommentResponseDTO;
import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.PreferredGender;
import com.mannazo.postservice.entity.TravelStatus;
import com.mannazo.postservice.repository.CommentRepository;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.service.CommentService;
import com.mannazo.postservice.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(SpringExtension.class) // JUnit 5에 필요한 확장자
@SpringBootTest // 실제 Spring Boot 애플리케이션 컨텍스트 로드
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserServiceClient userServiceClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final Random random = new Random();

    @Test
    public void testCreateMultiplePosts() {
        int numberOfPosts = 20; // 생성할 포스트 개수

        List<UUID> userIds = userServiceClient.getAllUserIds();

        log.info("UserIds::::>" + userIds);

        for (int i = 0; i < numberOfPosts; i++) {
            // Given
            UUID userId = userIds.get(random.nextInt(userIds.size()));
            String travelNationality = generateRandomCountry();
            String travelCity = generateRandomCity();
            LocalDate travelStartDate = generateRandomDate();
            LocalDate travelEndDate = travelStartDate.plusDays(random.nextInt(10) + 1); // 여행 기간은 시작일로부터 1~10일
            String travelStatus = getRandomTravelStatus();
            String preferredGender = getRandomPreferredGender();
            String travelStyle = generateRandomTravelStyle();
            String travelPurpose = generateRandomTravelPurpose();
            List<String> imageUrls = generateRandomImageUrls();

            PostRequestDTO postRequestDTO = new PostRequestDTO();
            postRequestDTO.setUserId(userId);
            postRequestDTO.setTravelNationality(travelNationality);
            postRequestDTO.setTravelCity(travelCity);
            postRequestDTO.setTravelStartDate(travelStartDate);
            postRequestDTO.setTravelEndDate(travelEndDate);
            postRequestDTO.setTravelStatus(travelStatus);
            postRequestDTO.setPreferredGender(preferredGender);
            postRequestDTO.setTravelStyle(travelStyle);
            postRequestDTO.setTravelPurpose(travelPurpose);
            postRequestDTO.setImageUrls(imageUrls);

            // When
            PostResponseDTO actualResponseDTO = postService.createPost(postRequestDTO);

            // Then
            assertEquals(travelNationality, actualResponseDTO.getTravelNationality());
            assertEquals(travelCity, actualResponseDTO.getTravelCity());
            assertEquals(travelStartDate, actualResponseDTO.getTravelStartDate());
            assertEquals(travelEndDate, actualResponseDTO.getTravelEndDate());
            assertEquals(travelStatus, actualResponseDTO.getTravelStatus());
            assertEquals(preferredGender, actualResponseDTO.getPreferredGender());
            assertEquals(travelStyle, actualResponseDTO.getTravelStyle());
            assertEquals(travelPurpose, actualResponseDTO.getTravelPurpose());
        }
    }

    // 무작위로 나라 이름 생성
    private String generateRandomCountry() {
        List<String> countries = Arrays.asList("Korea", "USA", "Japan", "Germany", "France", "Italy", "Australia");
        return countries.get(random.nextInt(countries.size()));
    }

    // 무작위로 도시 이름 생성
    private String generateRandomCity() {
        List<String> cities = Arrays.asList("Seoul", "New York", "Tokyo", "Berlin", "Paris", "Rome", "Sydney");
        return cities.get(random.nextInt(cities.size()));
    }

    // 무작위로 여행 스타일 생성
    private String generateRandomTravelStyle() {
        List<String> styles = Arrays.asList("Adventure", "City Tour", "Beach", "Camping", "Hiking");
        return styles.get(random.nextInt(styles.size()));
    }

    // 무작위로 여행 목적 생성
    private String generateRandomTravelPurpose() {
        List<String> purposes = Arrays.asList("Vacation", "Business Trip", "Honeymoon", "Study Abroad", "Visiting Friends");
        return purposes.get(random.nextInt(purposes.size()));
    }

    // 무작위로 이미지 URL 생성
    private List<String> generateRandomImageUrls() {
        List<String> imageUrls = new ArrayList<>();
        int numImages = random.nextInt(5) + 1; // 1~5개의 이미지 URL 생성
        for (int i = 0; i < numImages; i++) {
            imageUrls.add("https://picsum.photos/200/300?random" + (i + 1));
        }
        return imageUrls;
    }

    // 무작위로 여행 상태 생성
    private String getRandomTravelStatus() {
        TravelStatus[] statuses = TravelStatus.values();
        return statuses[random.nextInt(statuses.length)].name();
    }

    // 무작위로 선호하는 성별 생성
    private String getRandomPreferredGender() {
        PreferredGender[] genders = PreferredGender.values();
        return genders[random.nextInt(genders.length)].name();
    }

    // 무작위로 날짜 생성 (2023년부터 2025년 사이)
    private LocalDate generateRandomDate() {
        return LocalDate.of(2023 + random.nextInt(3), random.nextInt(12) + 1, random.nextInt(28) + 1);
    }
}
