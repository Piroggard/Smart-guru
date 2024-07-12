package org.example.repository;

import org.example.model.AdressCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryAddress extends JpaRepository<AdressCourse, UUID> {
    AdressCourse findAdressCourseByCourseId (UUID id);
}
