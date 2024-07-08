package org.example.contriller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CourseRequestDto;
import org.example.dto.CourseRequestUpdateDto;
import org.example.dto.CourseResponseDto;
import org.example.servise.CurseService;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")

public class CourseController {

    private final CurseService curseService;

    @PostMapping
    public UUID createCourse(@RequestBody CourseRequestDto course) {
        log.info("Метод createCourse" + course);
        return curseService.createCourse(course);
    }

    @PatchMapping
    public UUID updateCourse(@RequestBody CourseRequestUpdateDto courseRequestUpdateDto) {
        log.info("Метод updateCourse{}", courseRequestUpdateDto);
        return curseService.updateCourse(courseRequestUpdateDto);
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