package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.servise.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public UUID createCourse(@RequestBody CourseCreationDto course) {
        log.info("Request to create a course received {}", course.getCourse().getName());
        return courseService.createCourse(course);
    }

    @PatchMapping
    public UUID updateCourse(@RequestBody CourseUpdateRequestDto courseUpdarionDto) {
        log.info("Request for course update received {}", courseUpdarionDto.getCourse().getName());
        return courseService.updateCourse(courseUpdarionDto);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable UUID courseId) {
        log.info("Received a request to delete a course {}", courseId);
        courseService.deleteCourse(courseId);
    }

    @GetMapping("/{courseId}")
    public CourseResponseDto getCourses(@PathVariable UUID courseId) {
        log.info("Request for detailed course information received {}", courseId);
        return courseService.getCourses(courseId);
    }
}