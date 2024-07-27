package org.example.repository;

import org.example.enam.DirectionEnum;
import org.example.model.Course;
import org.example.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<News, UUID> {

    List<News> findByDeleteFalse();

    List<News> findByDirection(DirectionEnum direction);

    List<News> findByCourse(Course course);

    List<News> findByDirectionAndCourse(DirectionEnum direction, Course course);
}
