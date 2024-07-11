package com.mannazo.postservice.repository;

import com.mannazo.postservice.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID>, JpaSpecificationExecutor<PostEntity> {
    Page<PostEntity> findAll(Pageable pageable);

    Page<PostEntity> findAll(Specification<PostEntity> spec, Pageable pageable);
}
