package org.example.course.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.course.controller.error.ApiError;
import org.example.dto.CourseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@Slf4j
@RequiredArgsConstructor
public class CourseController {

    @GetMapping
    public String test (){
        return "Я работаю!";
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiError add (@RequestBody CourseDto courseDto){
        System.out.printf("" + courseDto);
        return ApiError.builder()
                .errorCode(0).build();
    }
}
