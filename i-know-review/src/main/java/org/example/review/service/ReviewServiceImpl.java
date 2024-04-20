package org.example.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.db.ReviewStorage;
import org.example.review.dto.ReviewDto;
import org.example.review.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewServiceInterface {
    private final ReviewStorage reviewStorage;


    public ReviewDto getReviewById(Long reviewId) {
        return reviewStorage.getReviewById(reviewId);
    }

    public ReviewDto addReview(ReviewDto reviewDto) {
        return reviewStorage.addReview(reviewDto);
    }

    public ReviewDto updateReview(ReviewDto reviewDto, Long reviewId) {
        if(reviewStorage.getReviewById(reviewId).equals(null)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        }
        return reviewStorage.updateReview(reviewDto);
    }

    public void deleteReview(Long reviewId) {
        if(reviewStorage.getReviewById(reviewId).equals(null)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        } else {
            reviewStorage.deleteReview(reviewId);
        }
    }

    public Page<ReviewDto> getAllReviewsByUserId(Long userId, Pageable pageable) {
        Page<ReviewDto> reviews = reviewStorage.getAllReviewsByUserId(userId,pageable);
        return reviews;
    }
}
