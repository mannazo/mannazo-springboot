package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.CommunityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, UUID>, CommunityCustomRepository {
    Page<CommunityEntity> findAll(Pageable pageable);
}
