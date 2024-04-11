package org.example.review.service;

import org.example.review.dto.ReviewDto;

public interface ReviewServiceInterface {
    ReviewDto getReviewById(Long reviewId);
    ReviewDto addReview(ReviewDto reviewDto);
    ReviewDto updateReview(ReviewDto reviewDto, Long reviewId);
    public void deleteReview(Long reviewId);
}
