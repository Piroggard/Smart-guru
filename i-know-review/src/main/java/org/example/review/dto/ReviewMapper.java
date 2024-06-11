package org.example.review.dto;

import org.example.review.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);

}