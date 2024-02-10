package org.example.repository;

import org.example.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository <Photos, Long> {
}
