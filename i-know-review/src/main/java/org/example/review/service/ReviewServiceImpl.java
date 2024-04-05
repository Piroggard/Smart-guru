package org.example.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.db.ReviewStorage;
import org.example.review.dto.ReviewDto;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewServiceInterface {
    private final ReviewStorage reviewStorage;


    public ReviewDto getReviewById(Long reviewId) {
        return reviewStorage.getReviewById(reviewId);
    }
}
