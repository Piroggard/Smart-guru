package org.example.mappers;

import dto.NewsDto;
import org.example.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    News toNews(NewsDto newsDto);
}
