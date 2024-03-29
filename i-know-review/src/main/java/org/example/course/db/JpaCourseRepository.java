package org.example.course.db;

import org.example.course.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JpaCourseRepository extends PagingAndSortingRepository<Course, Long> {
    Course getCourseById(Long courseId);

}
