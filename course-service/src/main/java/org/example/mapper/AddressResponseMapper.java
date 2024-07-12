package org.example.mapper;

import org.example.dto.AddressResponseDto;
import org.example.model.AdressCourse;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AddressResponseMapper {
    AddressResponseDto toDto (AdressCourse adressCourse);
}
