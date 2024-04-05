package org.example.review.dto;

import org.example.review.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewDto toDto(Review review) {
        if (review == null) {
            return null;
        }
        return ReviewDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .description(review.getDescription())
                .postDate(review.getPostDate())
                .userId(review.getUserId())
                .build();
    }

    public static Review toEntity(ReviewDto reviewDto) {
        if (reviewDto == null) {
            return null;
        }
        return Review.builder()
                .id(reviewDto.getId())
                .title(reviewDto.getTitle())
                .description(reviewDto.getDescription())
                .postDate(reviewDto.getPostDate())
                .userId(reviewDto.getUserId())
                .build();
    }
}
