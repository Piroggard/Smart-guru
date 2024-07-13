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
    public UUID createCourse(@RequestBody CourseCreationDTO course) {
        log.info("Метод createCourse" + course);
        return curseService.createCourse(course);
    }

    @PatchMapping
    public UUID updateCourse(@RequestBody CourseUpdarionDto courseUpdarionDto) {
        log.info("Метод updateCourse{}", courseUpdarionDto);
        return curseService.updateCourse(courseUpdarionDto);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable UUID courseId) {
        log.info("Метод deleteCourse " + courseId);
        curseService.deleteCourse(courseId);
    }

    @GetMapping("/{courseId}")
    public CourseResponseDto getCourses(@PathVariable UUID courseId) {
        log.info("Метод getCourses " + courseId);
        return curseService.getCourses(courseId);
    }
}