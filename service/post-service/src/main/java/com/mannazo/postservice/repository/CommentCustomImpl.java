package com.mannazo.postservice.repository;

import com.mannazo.postservice.dto.CommentResponseDTO;
import com.mannazo.postservice.entity.CommentEntity;
import com.mannazo.postservice.entity.QCommentEntity;
import com.mannazo.postservice.entity.QPostEntity;
import com.mannazo.postservice.mapStruct.commnet.CommentResponseMapStruct;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommentCustomImpl implements CommentCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final CommentResponseMapStruct commentResponseMapStruct;
    @Override
    public List<CommentResponseDTO> getCommentsByPostId(UUID postId) {
        QCommentEntity qComment = QCommentEntity.commentEntity;
        QPostEntity qPost = QPostEntity.postEntity;

        List<CommentEntity> comments = jpaQueryFactory.select(qComment)
                .from(qComment)
                .leftJoin(qComment.post, qPost).fetchJoin()
                .where(qPost.postId.eq(postId))
                .orderBy(qComment.createAt.desc())
                .fetch();

        return comments.stream()
                .map(commentResponseMapStruct::toResponseDTO)
                .collect(Collectors.toList());
    }

}
