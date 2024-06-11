package org.example.contriller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.DirectionDTOResponse;
import org.example.dto.StatusDTOResponse;
import org.example.model.Course;
import org.example.servise.CurseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
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
    public Course createCourse(Course course) {
        log.info("Метод createCourse");
        return curseService.createCourse(course);
    }
}