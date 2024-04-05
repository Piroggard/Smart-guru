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
    @Autowired
    private final ReviewMapper reviewMapper;


    public ReviewDto getReviewById(Long reviewId) {
        return reviewMapper.toDto(jpaReviewRepository.getReviewById(reviewId));
    }

    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = ReviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(jpaReviewRepository.save(review));
    }

    public ReviewDto updateReview(ReviewDto reviewDto) {
        Review review = ReviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(jpaReviewRepository.save(review));
    }
}
