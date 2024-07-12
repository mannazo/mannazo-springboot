package com.mannazo.communityservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mannazo.communityservice.client.UserServiceClient;
import com.mannazo.communityservice.entity.CommunityEntity;
import com.mannazo.communityservice.entity.ImageEntity;
import com.mannazo.communityservice.repository.CommunityRepository;
import com.mannazo.communityservice.repository.ImageRepository;
import com.mannazo.communityservice.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class) // JUnit 5에 필요한 확장자
@SpringBootTest // 실제 Spring Boot 애플리케이션 컨텍스트 로드
public class CommunityTest {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserServiceClient userServiceClient;

    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMultipleCommunities() {
        int numberOfCommunities = 20; // 생성할 포스트 개수

        List<UUID> userIds = userServiceClient.getAllUserIds();

        for (int i = 0; i < numberOfCommunities; i++) {
            // Given
            UUID userId = userIds.get(random.nextInt(userIds.size()));
            String title = generateRandomTitle();
            String description = "이 커뮤니티는 " + title + "에 대해 토론하는 곳입니다.";
            LocalDateTime lastUpdated = LocalDateTime.now();
            Long viewCount = (long) random.nextInt(1000); // 0부터 999 사이의 랜덤한 조회수
            List<ImageEntity> images = generateRandomImages(); // 이미지 리스트 생성

            CommunityEntity community = new CommunityEntity();
            community.setUserId(userId);
            community.setTitle(title);
            community.setDescription(description);
            community.setLastUpdated(lastUpdated);
            community.setViewCount(viewCount);

            // CommunityEntity 저장
            communityRepository.save(community);

            // 생성된 이미지들에 커뮤니티 설정
            for (ImageEntity image : images) {
                image.setCommunity(community); // 이미지에 커뮤니티 설정
            }

            // When: 이미지 저장
            imageRepository.saveAll(images);

            // Then (Optional: Validation or Logging)
            log.info("저장된 커뮤니티 ID: " + community.getCommunityId());
        }

        // Example validation after saving
        List<CommunityEntity> savedCommunities = communityRepository.findAll();
        assertEquals(numberOfCommunities, savedCommunities.size(), "저장된 커뮤니티 개수가 일치하지 않습니다.");
    }


    // 무작위로 제목 생성
    private String generateRandomTitle() {
        List<String> titles = List.of("여행 커뮤니티", "기술 토론 그룹", "음식 공유 모임", "헬스 강좌");
        return titles.get(random.nextInt(titles.size()));
    }

    // 무작위로 상태 생성
    private String getRandomStatus() {
        List<String> statuses = List.of("활성", "비활성");
        return statuses.get(random.nextInt(statuses.size()));
    }

    // 무작위로 이미지 URL 생성
    private List<ImageEntity> generateRandomImages() {
        List<ImageEntity> images = new ArrayList<>();
        int numImages = random.nextInt(5) + 1; // 1~5개의 이미지 URL 생성
        for (int i = 0; i < numImages; i++) {
            ImageEntity image = new ImageEntity();
            image.setFilePath("https://example.com/image" + (i + 1) + ".jpg");
            // 이미지와 관련된 추가적인 설정을 할 수 있음
            images.add(image);
        }
        return images;
    }
}
