package org.example.repository;

import org.example.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryCourse extends JpaRepository<Course, UUID> {
}
