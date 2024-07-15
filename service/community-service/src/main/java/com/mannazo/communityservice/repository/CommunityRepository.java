package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.CommunityEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, UUID>, CommunityCustomRepository {
    Page<CommunityEntity> findAll(Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<CommunityEntity> findById(UUID communityId);

    @Query("SELECT c.communityId FROM CommunityEntity c")
    List<UUID> findAllCommunityIds();
}
