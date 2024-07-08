package com.mannazo.communityservice.repository;

import com.mannazo.communityservice.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, UUID> {

}
