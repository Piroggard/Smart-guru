package org.example.mapper;

import org.example.dto.CourseResponseDto;
import org.example.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseResponseMapper {

    CourseResponseDto toCourseDto(Course course);
}
