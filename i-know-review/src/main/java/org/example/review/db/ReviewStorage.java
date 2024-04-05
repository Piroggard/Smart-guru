package org.example.review.db;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.dto.ReviewDto;
import org.example.review.dto.ReviewMapper;
import org.example.review.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ReviewStorage {
    @Autowired
    private final JpaReviewRepository jpaReviewRepository;
    private ReviewMapper reviewMapper;


    public ReviewDto getReviewById(Long reviewId) {
        return reviewMapper.toDto(jpaReviewRepository.getReviewById(reviewId));
    }
}
