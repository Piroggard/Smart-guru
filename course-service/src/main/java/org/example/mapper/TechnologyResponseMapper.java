package org.example.mapper;

import org.example.dto.TechnologyResponseDto;
import org.example.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologyResponseMapper {

    TechnologyResponseDto toDto (Technology technology);
}
