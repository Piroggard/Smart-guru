package org.example.repository;

import org.example.model.AdressCourse;
import org.example.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdressRepository extends JpaRepository<AdressCourse, UUID> {

    AdressCourse findAdressCourseByCourse(Course course);
}
