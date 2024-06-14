package org.example.mapper;

import dto.NewsDto;
import dto.NewsDtoResponse;
import org.example.model.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    News toEntity(NewsDto newsDto);
    NewsDtoResponse toDTO(News news);
}

