package com.org.example.db;

import com.org.example.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaCertificateRepository extends JpaRepository<Certificate, UUID> {
    Certificate getCertificateByCertificateId(UUID certificateId);
    void deleteCertificateByCertificateId(UUID certificateId);
    List<Certificate> findAllCertificatesByCertificateId(UUID certificateId);
}
