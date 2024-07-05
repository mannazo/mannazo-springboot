package com.mannazo.postservice.service.impl;

import com.mannazo.postservice.domain.PostRequestDTO;
import com.mannazo.postservice.domain.PostResponseDTO;
import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.Status;
import com.mannazo.postservice.entity.WGender;
import com.mannazo.postservice.repository.PostRepository;
import com.mannazo.postservice.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public PostResponseDTO getPost(UUID tripId) {
        Optional<PostEntity> postEntity = postRepository.findById(tripId);
        PostEntity entity = postEntity.get();

        PostResponseDTO trip = new PostResponseDTO();

        trip.setTripId(entity.getTripId());
        trip.setUserId(entity.getUserId());
        trip.setTNationality(entity.getTNationality());
        trip.setTCity(entity.getTCity());
        trip.setStartDate(String.valueOf(entity.getStartDate()));
        trip.setEndDate(String.valueOf(entity.getEndDate()));
        trip.setStatus(String.valueOf(entity.getStatus()));
        trip.setWGender(String.valueOf(entity.getWGender()));
        trip.setTStyle(entity.getTStyle());
        trip.setTGoal(entity.getTGoal());

        return trip;
    }

    @Override
    public List<PostResponseDTO> findALL() {

        List<PostEntity> postEntityList = postRepository.findAll();

        return postEntityList.stream()
                .map(entity -> {
                    PostResponseDTO dto = new PostResponseDTO();
                    dto.setTripId(entity.getTripId());
                    dto.setUserId(entity.getUserId());
                    dto.setTNationality(entity.getTNationality());
                    dto.setTCity(entity.getTCity());
                    dto.setStartDate(String.valueOf(entity.getStartDate()));
                    dto.setEndDate(String.valueOf(entity.getEndDate()));
                    dto.setStatus(String.valueOf(entity.getStatus()));
                    dto.setWGender(String.valueOf(entity.getWGender()));
                    dto.setTStyle(entity.getTStyle());
                    dto.setTGoal(entity.getTGoal());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addPost(PostRequestDTO trip) {
        PostEntity postEntity = new PostEntity();

        postEntity.setTripId(trip.getTripId());
        postEntity.setUserId(trip.getUserId());\
        postEntity.setTNationality(trip.getTNationality());
        postEntity.setTCity(trip.getTCity());
        postEntity.setStartDate(LocalDate.parse(trip.getStartDate()));
        postEntity.setEndDate(LocalDate.parse(trip.getEndDate()));
        postEntity.setStatus(Status.valueOf(trip.getStatus()));
        postEntity.setWGender(WGender.valueOf(trip.getWGender()));
        postEntity.setTStyle(trip.getTStyle());
        postEntity.setTGoal(trip.getTGoal());

        postRepository.save(postEntity);
    }

    @Override
    public void deletePost(UUID tripId) {

        postRepository.deleteById(tripId);

    }

    @Override
    public void updatePost(UUID tripId, PostRequestDTO trip) {

        PostEntity postEntity = postRepository.findById(tripId).orElse(null);

        postEntity.setTripId(trip.getTripId());
        postEntity.setUserId(trip.getUserId());
        postEntity.setTNationality(trip.getTNationality());
        postEntity.setTCity(trip.getTCity());
        postEntity.setStartDate(LocalDate.parse(trip.getStartDate()));
        postEntity.setEndDate(LocalDate.parse(trip.getEndDate()));
        postEntity.setStatus(Status.valueOf(trip.getStatus()));
        postEntity.setWGender(WGender.valueOf(trip.getWGender()));
        postEntity.setTStyle(trip.getTStyle());
        postEntity.setTGoal(trip.getTGoal());
    }
}
