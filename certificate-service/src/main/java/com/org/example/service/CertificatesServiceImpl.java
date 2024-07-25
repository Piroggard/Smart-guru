package com.org.example.service;

import com.org.example.db.CertificateRepository;
import com.org.example.dto.CertificateDto;
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
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;

    @Transactional
    @Override
    public CertificateDto saveCertificate(CertificateDto certificateDto) {
        log.info("Adding certificate: {}", certificateDto);
        Certificate certificate = certificateMapper.toEntity(certificateDto);
        CertificateDto savedCertificate = certificateMapper.toDto(certificateRepository.save(certificate));
        log.info("The certificate: {} is added", savedCertificate);
        return savedCertificate;
    }

    @Transactional
    @Override
    public CertificateDto updateCertificate(CertificateDto certificateDto) {
        existingCertificate(certificateDto.getId());
        log.info("Updating certificate: {}", certificateDto);
        Certificate certificateToSave = certificateMapper.toEntity(certificateDto);
        CertificateDto savedCertificate = certificateMapper.toDto(certificateRepository.save(certificateToSave));
        log.info("The certificate: {} is updated", savedCertificate);
        return savedCertificate;
    }

    @Transactional
    @Override
    public CertificateDto getCertificate(UUID certificateId) {
        if (certificateRepository.findById(certificateId).isPresent()) {
            Certificate existingCertificate = certificateRepository.findById(certificateId).get();
            if (existingCertificate.getDelete() != null) {
                if (existingCertificate.getDelete()) {
                    throw new NotFoundException("Certificate with id " + certificateId + " is deleted");
                }
            }
            log.info("Getting certificate: {}", certificateId);
            return certificateMapper.toDto(certificateRepository.findById(certificateId).get());
        } else {
            throw new NotFoundException("Certificate not found");
        }
    }


    @Transactional
    @Override
    public void deleteCertificate(UUID certificateId) {
        log.info("Deleting certificate: {}", certificateId);
        //Soft Delete
        Certificate deletedCertificate = existingCertificate(certificateId);
        deletedCertificate.setDelete(true);
        certificateRepository.save(deletedCertificate);
        log.info("The certificate deleted: {}", certificateId);
    }

    private Certificate existingCertificate(UUID certificateId) {
       return certificateRepository.findById(certificateId)
                .orElseThrow(() -> new NotFoundException("Certificate not found with id: " + certificateId));
    }
}
