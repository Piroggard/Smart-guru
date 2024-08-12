package com.org.example.mapper;

import com.org.example.dto.CertificateDto;
import com.org.example.model.Certificate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificateMapper {
    Certificate toEntity(CertificateDto certificateDto);
    CertificateDto toDto(Certificate certificate);
}
