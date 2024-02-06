package org.example.course.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.course.controller.error.ApiError;
import org.example.dto.CourseDto;
import org.example.model.Course;
import org.example.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")

@Slf4j
@RequiredArgsConstructor
@AllArgsConstructor
public class CourseController {
    private CourseRepository courseRepository;

    @GetMapping

    public String test (){
        return "Я работаю!";
    }


    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)
    public ApiError add (@RequestBody Course courseDto){
        courseRepository.save(courseDto);
        System.out.printf("" + courseDto);
        return ApiError.builder()
                .errorCode(0).build();
    }
}
