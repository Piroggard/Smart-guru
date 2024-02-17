package org.example.repository;

import org.example.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotosRepository extends JpaRepository<Photos, Long> {
    List<Photos> findByCourse_Id(Long id);

}
