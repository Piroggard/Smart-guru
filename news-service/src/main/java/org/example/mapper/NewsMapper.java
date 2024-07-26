package org.example.mapper;

import org.example.dto.NewsCreationDto;
import org.example.dto.NewsResponseDto;
import org.example.dto.NewsUpdateDto;
import org.example.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    @Mapping(target = "course.id", source = "courseId")
    News toEntity(NewsCreationDto newsCreationDto);

    @Mapping(target = "course.id", source = "courseId")
    News toEntity(NewsUpdateDto newsUpdateDto);

    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "directionDescription", expression = "java(news.getDirection().getDescription())")
    NewsResponseDto toDto(News news);
}

