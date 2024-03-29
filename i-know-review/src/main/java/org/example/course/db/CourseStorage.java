package org.example.course.db;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.course.model.Course;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class CourseStorage {
    @Autowired
    private final JpaCourseRepository jpaCourseRepository;
    public Course getCourseById(Long courseId) {
        return jpaCourseRepository.getCourseById(courseId);
    }
}
