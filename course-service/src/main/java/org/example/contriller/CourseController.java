package org.example.contriller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.servise.CurseService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CurseService curseService;

    @PostMapping
    public UUID createCourse(@RequestBody CourseCreationDto course) {
        log.info("Request to create a course received {}", course.getCourse().getName());
        return curseService.createCourse(course);
    }

    @PatchMapping
    public UUID updateCourse(@RequestBody CourseUpdarionDto courseUpdarionDto) {
        log.info("Request for course update received {}", courseUpdarionDto.getCourse().getName());
        return curseService.updateCourse(courseUpdarionDto);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable UUID courseId) {
        log.info("Received a request to delete a course {}", courseId);
        curseService.deleteCourse(courseId);
    }

    @GetMapping("/{courseId}")
    public CourseResponseDto getCourses(@PathVariable UUID courseId) {
        log.info("Request for detailed course information received {}", courseId);
        return curseService.getCourses(courseId);
    }
}