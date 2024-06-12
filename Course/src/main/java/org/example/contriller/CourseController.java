package org.example.contriller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CourseRequestDTO;
import org.example.dto.CourseResponseDTO;
import org.example.dto.DirectionDTOResponse;
import org.example.dto.StatusDTOResponse;
import org.example.model.Course;
import org.example.servise.CurseService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CurseService curseService;

    @GetMapping("/status")
    public List<StatusDTOResponse> getStatus() {
        log.info("Метод getStatus");
        return curseService.getStatus();
    }

    @GetMapping("/direction")
    public List<DirectionDTOResponse> getDirection() {
        log.info("Метод getDirection");
        return curseService.getDirection();
    }

    @GetMapping()
    public String test() {
        log.info("Метод getStatus");
        return "OK";
    }

    @PostMapping()
    public UUID createCourse(@RequestBody CourseRequestDTO course) {
        log.info("Метод createCourse" + course);
        return curseService.createCourse(course);
    }

    @PatchMapping()
    public UUID patchCourse(@RequestBody CourseRequestDTO course) {
        log.info("Метод patchCourse " + course);
        return curseService.patchCourse(course);
    }

    @DeleteMapping ("/{courseId}")
    public void deleteCourse(@PathVariable Enum courseId) {
        log.info("Метод patchCourse " + courseId);
        curseService.deleteCourse(courseId);
    }
}