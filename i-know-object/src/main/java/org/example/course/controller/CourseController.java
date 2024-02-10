package org.example.course.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.course.controller.error.ApiError;
import org.example.course.controller.servise.CourseServise;
import org.example.dto.AddressDto;
import org.example.dto.CourseDto;
import org.example.model.Course;
import org.example.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/course")
@Slf4j
@AllArgsConstructor
@RestController
public class CourseController {
    CourseServise courseServise;


    @GetMapping

    public String test (){
        return "Я работаю!";
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course add (@RequestBody CourseDto courseDto){
        log.info("Меттод add " + courseDto);


        return courseServise.addCourse(courseDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    public void add (@RequestBody AddressDto courseDto){
        System.out.printf("" + courseDto);

    }


}
