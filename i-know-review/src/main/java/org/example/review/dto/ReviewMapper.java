package org.example.review.dto;

import org.example.review.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);

}