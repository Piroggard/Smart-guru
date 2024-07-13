package org.example.mapper;

import org.example.dto.PhotosResponseDto;
import org.example.model.PhotosCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PhotosResponseMapper {

    PhotosResponseDto toDto(PhotosCourse photosCourse);
}
