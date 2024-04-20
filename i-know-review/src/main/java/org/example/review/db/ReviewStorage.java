package org.example.review.db;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.dto.ReviewDto;
import org.example.review.dto.ReviewMapper;
import org.example.review.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void deleteReview(Long reviewId) {
        jpaReviewRepository.deleteReviewById(reviewId);
    }

    public Page<ReviewDto> getAllReviewsByUserId(Long userId, Pageable pageable) {
        Page<Review> reviewPage = jpaReviewRepository.getAllReviewsByUserId(userId, pageable);
        return reviewPage.map(reviewMapper::toDto);
    }
}
