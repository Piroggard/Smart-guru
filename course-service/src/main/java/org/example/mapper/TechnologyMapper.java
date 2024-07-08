package org.example.mapper;

import org.example.dto.TechnologyRequestDto;
import org.example.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologyMapper {
    Technology toTechnology(TechnologyRequestDto technologyRequestDto);
}
