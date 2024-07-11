package com.mannazo.postservice.repository.specification;

import com.mannazo.postservice.entity.PostEntity;
import com.mannazo.postservice.entity.PreferredGender;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PostSpecification {
    public static Specification<PostEntity> hasTravelCity(String travelCity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("travelCity"), travelCity);
    }

    public static Specification<PostEntity> hasPreferredGender(PreferredGender preferredGender) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("preferredGender"), preferredGender);
    }

    public static Specification<PostEntity> hasTravelStatus(String travelStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("travelStatus"), travelStatus);
    }

    public static Specification<PostEntity> hasTravelStyles(String[] travelStyles) {
        return (root, query, criteriaBuilder) -> {
            Specification<PostEntity> spec = Specification.where(null);
            for (String style : travelStyles) {
                spec = spec.or((root1, query1, criteriaBuilder1) -> criteriaBuilder1.like(root1.get("travelStyle"), "%" + style + "%"));
            }
            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }

    public static Specification<PostEntity> hasTravelNationalities(String[] travelNationalities) {
        return (root, query, criteriaBuilder) -> {
            Specification<PostEntity> spec = Specification.where(null);
            for (String nationality : travelNationalities) {
                spec = spec.or((root1, query1, criteriaBuilder1) -> criteriaBuilder1.like(root1.get("travelNationality"), "%" + nationality + "%"));
            }
            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }

    public static Specification<PostEntity> betweenDates(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate != null && endDate != null) {
                return criteriaBuilder.between(root.get("travelStartDate"), startDate, endDate);
            } else if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("travelStartDate"), startDate);
            } else if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("travelEndDate"), endDate);
            } else {
                return criteriaBuilder.conjunction(); // 아무 조건도 추가하지 않음
            }
        };
    }

}
