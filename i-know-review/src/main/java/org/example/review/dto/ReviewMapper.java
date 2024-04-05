package org.example.review.dto;

import org.example.review.model.Review;
import org.example.user.db.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;

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
                .build();
    }
}
