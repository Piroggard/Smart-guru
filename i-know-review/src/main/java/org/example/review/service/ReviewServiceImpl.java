package org.example.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.db.JpaReviewRepository;
import org.example.review.dto.ReviewDto;
import org.example.review.dto.ReviewMapper;
import org.example.review.exception.BadRequestException;
import org.example.review.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewServiceInterface {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewMapper reviewMapper;



    @Transactional
    public ReviewDto getReviewById(Long reviewId) {
        return reviewMapper.toDto(jpaReviewRepository.getReviewById(reviewId));
    }

    @Transactional
    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(jpaReviewRepository.save(review));
    }

    @Transactional
    public ReviewDto updateReview(ReviewDto reviewDto, Long reviewId) {
        if(jpaReviewRepository.getReviewById(reviewId).equals(null)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        }
        Review review = reviewMapper.toEntity(reviewDto);
        return reviewMapper.toDto(jpaReviewRepository.save(review));
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        if(jpaReviewRepository.getReviewById(reviewId).equals(null)) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Такого отзыва не существует" + reviewId);
        } else {
            jpaReviewRepository.deleteReviewById(reviewId);
        }
    }

    @Transactional
    public List<ReviewDto> getAllReviewsByUserId(Long userId) {
        List<Review> reviewList = jpaReviewRepository.getAllReviewsByUserId(userId);
        return reviewList.stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
}
