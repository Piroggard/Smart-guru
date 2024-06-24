package com.org.example.controller.service;

import com.org.example.db.CertificateRepository;
import com.org.example.dto.CertificateDto;
import com.org.example.exceptions.DuplicateCertificateException;
import com.org.example.exceptions.NotFoundException;
import com.org.example.mapper.CertificateMapper;
import com.org.example.model.Certificate;
import com.org.example.service.CertificatesServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CertificateServiceImplTest {
    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private CertificateMapper certificateMapper;
    @InjectMocks
    private CertificatesServiceImpl certificatesServiceImpl;

    private UUID certificateId;
    private Certificate certificate;
    private CertificateDto certificateDto;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        initializeTestData();
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }

    private void initializeTestData() {
        UUID certificateId = UUID.randomUUID();
        certificate = new Certificate();
        certificate.setCertificateId(certificateId);

        certificateDto = new CertificateDto();
        certificateDto.setCertificateId(certificateId);
    }

    @Test
    public void testGetCertificate() {
        when(certificateRepository.getCertificateByCertificateId(certificateId))
                .thenReturn(certificate);
        when(certificateMapper.toDto(certificate)).thenReturn(certificateDto);

        CertificateDto foundCertificateDto = certificatesServiceImpl.getCertificate(certificateId);

        verify(certificateRepository).getCertificateByCertificateId(certificateId);
        verify(certificateMapper).toDto(certificate);

        assertNotNull(foundCertificateDto);
        assertEquals(certificateId, foundCertificateDto.getCertificateId());
    }
    @Test
    public void testGetCertificateById_NotFound() {
        when(certificateRepository.getCertificateByCertificateId(certificateId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            certificatesServiceImpl.getCertificate(certificateId);
        });
    }
    @Test
    public void testAddCertificate() {
        when(certificateMapper.toEntity(certificateDto)).thenReturn(certificate);
        when(certificateRepository.save(certificate)).thenReturn(certificate);
        when(certificateMapper.toDto(certificate)).thenReturn(certificateDto);

        CertificateDto result = certificatesServiceImpl.addCertificate(certificateDto);

        verify(certificateMapper).toEntity(certificateDto);
        verify(certificateRepository).save(certificate);
        verify(certificateMapper).toDto(certificate);

        assertNotNull(result);
        assertEquals(certificateDto.getCertificateId(), result.getCertificateId());
    }

    @Test
    public void testAddCertificate_ShouldThrowDuplicateCertificateException_WhenCertificateIsDuplicate() {
        when(certificateRepository.getCertificateByCertificateId(certificateId)).thenReturn(certificate);

        assertThrows(DuplicateCertificateException.class, () -> {
            certificatesServiceImpl.addCertificate(certificateDto);
        });

        verify(certificateRepository).getCertificateByCertificateId(certificateId);
        verify(certificateRepository, never()).save(any(Certificate.class));

    }
    @Test
    public void updateCertificate_ShouldReturnUpdatedCertificateDto_WhenCertificateExists() {
        when(certificateMapper.toEntity(certificateDto)).thenReturn(certificate);
        when(certificateRepository.getCertificateByCertificateId(certificateId)).thenReturn(certificate);
        when(certificateRepository.save(certificate)).thenReturn(certificate);
        when(certificateMapper.toDto(certificate)).thenReturn(certificateDto);

        CertificateDto result = certificatesServiceImpl.updateCertificate(certificateDto);

        verify(certificateMapper).toEntity(certificateDto);
        verify(certificateRepository).getCertificateByCertificateId(certificateId);
        verify(certificateRepository).save(certificate);
        verify(certificateMapper).toDto(certificate);

        assertNotNull(result);
        assertEquals(certificateDto.getCertificateId(), result.getCertificateId());
    }

    @Test
    public void updateCertificate_ShouldThrowNotFoundException_WhenCertificateDoesNotExist() {
        when(certificateMapper.toEntity(certificateDto)).thenReturn(certificate);
        when(certificateRepository.getCertificateByCertificateId(certificateId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            certificatesServiceImpl.updateCertificate(certificateDto);});

        verify(certificateMapper).toEntity(certificateDto);
        verify(certificateRepository).getCertificateByCertificateId(certificateId);
        verify(certificateRepository, never()).save(any(Certificate.class));
        verify(certificateMapper, never()).toDto(any(Certificate.class));
    }

    @Test
    public void deleteCertificate_ShouldThrowNotFoundException_WhenCertificateDoesNotExist() {
        when(certificateRepository.getCertificateByCertificateId(certificateId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            certificatesServiceImpl.deleteCertificate(certificateId);
        });

        verify(certificateRepository).getCertificateByCertificateId(certificateId);
        verify(certificateRepository, never()).delete(any(Certificate.class));
    }

    @Test
    public void deleteCertificate_ShouldDeleteCertificate_WhenCertificateExists() {
        when(certificateRepository.getCertificateByCertificateId(certificateId)).thenReturn(certificate);

        certificatesServiceImpl.deleteCertificate(certificateId);

        verify(certificateRepository).getCertificateByCertificateId(certificateId);
        verify(certificateRepository).delete(certificate);
    }


}
