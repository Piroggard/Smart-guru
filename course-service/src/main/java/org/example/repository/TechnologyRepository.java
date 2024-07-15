package org.example.repository;

import org.example.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnologyRepository extends JpaRepository<Technology, UUID> {

    Technology findTechnologiesByCourseId(UUID id);
}
