package org.example.repository;

import org.example.model.Course;
import org.example.model.PhotosCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PhotosRepository extends JpaRepository<PhotosCourse, UUID> {

    List<PhotosCourse> findAllByCourseId(UUID courseId);
}
