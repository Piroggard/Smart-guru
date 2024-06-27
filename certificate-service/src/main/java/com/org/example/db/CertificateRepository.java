package com.org.example.db;

import com.org.example.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
}
