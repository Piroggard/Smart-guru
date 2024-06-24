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

import java.util.Optional;
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
        certificate.setId(certificateId);

        certificateDto = new CertificateDto();
        certificateDto.setId(certificateId);
    }

    @Test
    public void testGetCertificate() {
        when(certificateRepository.findById(certificateId))
                .thenReturn(certificate);
        when(certificateMapper.toDto(certificate)).thenReturn(certificateDto);

        CertificateDto foundCertificateDto = certificatesServiceImpl.getCertificate(certificateId);

        verify(certificateRepository).findById(certificateId);
        verify(certificateMapper).toDto(certificate);

        assertNotNull(foundCertificateDto);
        assertEquals(certificateId, foundCertificateDto.getId());
    }
    @Test
    public void testGetCertificateById_NotFound() {
        when(certificateRepository.findById(certificateId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            certificatesServiceImpl.getCertificate(certificateId);
        });
    }
    @Test
    public void testAddCertificate() {
        when(certificateMapper.toEntity(certificateDto)).thenReturn(certificate);
        when(certificateRepository.save(certificate)).thenReturn(certificate);
        when(certificateMapper.toDto(certificate)).thenReturn(certificateDto);

        CertificateDto result = certificatesServiceImpl.saveCertificate(certificateDto);

        verify(certificateMapper).toEntity(certificateDto);
        verify(certificateRepository).save(certificate);
        verify(certificateMapper).toDto(certificate);

        assertNotNull(result);
        assertEquals(certificateDto.getId(), result.getId());
    }

    @Test
    public void testAddCertificate_ShouldThrowDuplicateCertificateException_WhenCertificateIsDuplicate() {
        when(certificateRepository.findById(certificateId)).thenReturn(Optional.ofNullable(certificate));

        assertThrows(DuplicateCertificateException.class, () -> {
            certificatesServiceImpl.saveCertificate(certificateDto);
        });

        verify(certificateRepository).findById(certificateId);
        verify(certificateRepository, never()).save(any(Certificate.class));

    }
    @Test
    public void updateCertificate_ShouldReturnUpdatedCertificateDto_WhenCertificateExists() {
        when(certificateMapper.toEntity(certificateDto)).thenReturn(certificate);
        when(certificateRepository.findById(certificateId)).thenReturn(Optional.ofNullable(certificate));
        when(certificateRepository.save(certificate)).thenReturn(certificate);
        when(certificateMapper.toDto(certificate)).thenReturn(certificateDto);

        CertificateDto result = certificatesServiceImpl.updateCertificate(certificateDto);

        verify(certificateMapper).toEntity(certificateDto);
        verify(certificateRepository).findById(certificateId);
        verify(certificateRepository).save(certificate);
        verify(certificateMapper).toDto(certificate);

        assertNotNull(result);
        assertEquals(certificateDto.getId(), result.getId());
    }

    @Test
    public void updateCertificate_ShouldThrowNotFoundException_WhenCertificateDoesNotExist() {
        when(certificateMapper.toEntity(certificateDto)).thenReturn(certificate);
        when(certificateRepository.findById(certificateId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            certificatesServiceImpl.updateCertificate(certificateDto);});

        verify(certificateMapper).toEntity(certificateDto);
        verify(certificateRepository).findById(certificateId);
        verify(certificateRepository, never()).save(any(Certificate.class));
        verify(certificateMapper, never()).toDto(any(Certificate.class));
    }

    @Test
    public void deleteCertificate_ShouldThrowNotFoundException_WhenCertificateDoesNotExist() {
        when(certificateRepository.findById(certificateId)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            certificatesServiceImpl.deleteCertificate(certificateId);
        });

        verify(certificateRepository).findById(certificateId);
        verify(certificateRepository, never()).delete(any(Certificate.class));
    }

    @Test
    public void deleteCertificate_ShouldDeleteCertificate_WhenCertificateExists() {
        when(certificateRepository.findById(certificateId)).thenReturn(Optional.ofNullable(certificate));

        certificatesServiceImpl.deleteCertificate(certificateId);

        verify(certificateRepository).findById(certificateId);
        verify(certificateRepository).delete(certificate);
    }


}
