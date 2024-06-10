package org.example.mappers;

import org.example.dto.StatusDTOResponse;
import org.example.model.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperCurse {
    StatusDTOResponse toStatusDTO (Status status);
}
