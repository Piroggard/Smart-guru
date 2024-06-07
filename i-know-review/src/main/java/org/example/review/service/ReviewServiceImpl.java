package org.example.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.db.ReviewStorage;
import org.example.review.dto.ReviewDto;
import org.example.review.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewServiceInterface {
    private final ReviewStorage reviewStorage;


    @Transactional
    public ReviewDto getReviewById(Long reviewId) {
        return reviewStorage.getReviewById(reviewId);
    }

    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        return reviewStorage.addReview(reviewDto);
    }

    @Transactional
    public ReviewDto updateReview(ReviewDto reviewDto, Long reviewId) {
        if(reviewStorage.getReviewById(reviewId).equals(null)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        }
        return reviewStorage.updateReview(reviewDto);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        if(reviewStorage.getReviewById(reviewId).equals(null)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        } else {
            reviewStorage.deleteReview(reviewId);
        }
    }

    @Transactional
    public List<ReviewDto> getAllReviewsByUserId(Long userId) {
        return reviewStorage.getAllReviewsByUserId(userId);
    }
}
