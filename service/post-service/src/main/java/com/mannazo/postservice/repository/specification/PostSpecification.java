package com.mannazo.postservice.repository.specification;

import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {
    public static Specification<PostEntity> hasTravelCity(String travelCity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("travelCity"), travelCity);
    }

    public static Specification<PostEntity> hasPreferredGender(PreferredGender preferredGender) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("preferredGender"), preferredGender);
    }

    public static Specification<PostEntity> hasTravelStyle(String travelStyle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("travelStyle"), travelStyle);
    }
}
