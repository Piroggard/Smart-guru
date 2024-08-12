package org.example.review.service;

import org.example.review.dto.ReviewDto;

import java.util.UUID;

public interface ReviewServiceInterface {

    ReviewDto getReviewById(UUID reviewId);

    ReviewDto addReview(ReviewDto reviewDto);

    ReviewDto updateReview(ReviewDto reviewDto, UUID reviewId);

    void deleteReview(UUID reviewId);

}
