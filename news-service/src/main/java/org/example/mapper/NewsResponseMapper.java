package org.example.mapper;

import org.example.dto.NewsResponseDto;
import org.example.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsResponseMapper {
    @Mapping(target = "courseId", source = "course.id")
    NewsResponseDto toDto(News news);
}
