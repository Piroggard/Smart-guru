package org.example.review.db;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.review.dto.ReviewDto;
import org.example.review.dto.ReviewMapper;
import org.example.review.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    public List<ReviewDto> getAllReviewsByUserId(Long userId) {
        List<Review> reviewList = jpaReviewRepository.getAllReviewsByUserId(userId);
        List<ReviewDto> reviewDtoList = reviewList.stream()
                .map(review -> reviewMapper.toDto(review))
                .collect(Collectors.toList());
        return reviewDtoList;
    }
}
