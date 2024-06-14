package org.example.mapper;

import org.example.dto.StatusDtoResponse;
import org.example.model.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperCurse {
    StatusDtoResponse toStatusDto (Status status);
}
