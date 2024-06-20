package com.org.example.service;

import com.org.example.dto.CertificateDto;

import java.util.UUID;

public interface CertificatesService {
    CertificateDto getCertificate(UUID certificateId);
    CertificateDto addCertificate(CertificateDto certificateDto);
    CertificateDto updateCertificate(CertificateDto certificateDto);
    void deleteCertificate(UUID certificateId);

}
