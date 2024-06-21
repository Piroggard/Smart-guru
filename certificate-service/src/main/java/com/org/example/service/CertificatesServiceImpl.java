package com.org.example.service;

import com.org.example.db.JpaCertificateRepository;
import com.org.example.dto.CertificateDto;
import com.org.example.exceptions.DuplicateCertificateException;
import com.org.example.exceptions.NotFoundException;
import com.org.example.mapper.CertificateMapper;
import com.org.example.model.Certificate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CertificatesServiceImpl implements CertificatesService {
    private final JpaCertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    @Transactional
    @Override
    public CertificateDto addCertificate(CertificateDto certificateDto) {
        log.info("Adding certificate: {}", certificateDto);
        Certificate certificate = certificateMapper.toEntity(certificateDto);

        if(certificateRepository.findById(certificateDto.getCertificateId()).isPresent()) {
            throw new DuplicateCertificateException("Certificate with ID " + certificateDto.getCertificateId()
                    + " already exists.");
        } else {
            return certificateMapper.toDto(certificateRepository.save(certificate));
        }

    }
    @Transactional
    @Override
    public CertificateDto updateCertificate(CertificateDto certificateDto) {
        log.info("Updating certificate: {}", certificateDto);
        Certificate certificate = certificateMapper.toEntity(certificateDto);
        if (certificateRepository.getCertificateByCertificateId(certificate.getCertificateId()) != null) {
            return certificateMapper.toDto(certificateRepository.save(certificate));
        } else {
            log.info("Certificate with id {} not found", certificate.getCertificateId());
            throw new NotFoundException("Certificate not found");
        }

    }

    @Transactional
    @Override
    public CertificateDto getCertificate(UUID certificateId) {
        log.info("Getting certificate: {}", certificateId);
        Certificate certificate = certificateRepository.findById(certificateId).orElse(null);
        if (certificate != null) {
            return certificateMapper.toDto(certificate);
        } else {
            throw new NotFoundException("Certificate not found");
        }

    }

    @Transactional
    @Override
    public void deleteCertificate(UUID certificateId) {
        log.info("Deleting certificate: {}", certificateId);
        Certificate certificate = certificateRepository.findById(certificateId).orElse(null);
        if (certificate != null) {
            certificateRepository.delete(certificate);
        } else {
            log.info("Certificate with id {} not found", certificateId);
            throw new NotFoundException("Certificate with id " + certificateId + " not found");
        }

    }


}
