package org.example.repository;

import org.example.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCourse extends JpaRepository<Course, Enum> {
}
