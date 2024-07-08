package com.mannazo.postservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mannazo.postservice.dto.PostRequestDTO;
import com.mannazo.postservice.dto.PostResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import com.mannazo.postservice.entity.TravelStatus;
import com.mannazo.postservice.mapStruct.PostRequestMapStruct;
import com.mannazo.postservice.mapStruct.PostResponseMapStruct;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class) // JUnit 5에 필요한 확장자
@SpringBootTest // 실제 Spring Boot 애플리케이션 컨텍스트 로드
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostResponseMapStruct postResponseMapStruct;

    @Autowired
    private PostRequestMapStruct postRequestMapStruct;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePost() {
        // Given
        UUID userId = UUID.randomUUID();
        String travelNationality = "Korea";
        String travelCity = "Seoul";
        LocalDate travelStartDate = LocalDate.of(2024, 7, 10);
        LocalDate travelEndDate = LocalDate.of(2024, 7, 20);
        String travelStatus = String.valueOf(TravelStatus.등록);
        String preferredGender = String.valueOf(PreferredGender.여자);
        String travelStyle = "Adventure";
        String travelPurpose = "Vacation in Seoul";
        List<String> imageUrls = Arrays.asList(
                "https://example.com/image1.jpg",
                "https://example.com/image2.jpg",
                "https://example.com/image3.jpg",
                "https://example.com/image4.jpg",
                "https://example.com/image5.jpg"
        );
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