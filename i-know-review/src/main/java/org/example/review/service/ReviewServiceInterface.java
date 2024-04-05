package org.example.review.service;

import org.example.review.dto.ReviewDto;

public interface ReviewServiceInterface {
    public ReviewDto getReviewById(Long reviewId);
}
