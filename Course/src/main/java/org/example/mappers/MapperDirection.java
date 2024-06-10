package org.example.mappers;


import org.example.dto.DirectionDTOResponse;
import org.example.model.Direction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperDirection {
    DirectionDTOResponse toDirectionDTO (Direction direction);
}
