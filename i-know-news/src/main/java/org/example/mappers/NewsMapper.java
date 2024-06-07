package org.example.mappers;

import dto.NewsDto;
import dto.NewsDtoResponse;
import org.example.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    News toDto(NewsDto newsDto);

    NewsDtoResponse toEntity(News news);
}
