package org.example.mappers;


import org.example.dto.DirectionDtoResponse;
import org.example.model.Direction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperDirection {
    DirectionDtoResponse toDirectionDTO (Direction direction);
}
