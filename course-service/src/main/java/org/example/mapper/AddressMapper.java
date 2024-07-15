package org.example.mapper;

import org.example.dto.AddressRequestDto;
import org.example.model.AdressCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AdressCourse toAdressCourse(AddressRequestDto addressRequestDto);
}
