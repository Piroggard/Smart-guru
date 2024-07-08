package org.example.repository;

import org.example.model.PhotosCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryPhotos extends JpaRepository<PhotosCourse, UUID> {
}
