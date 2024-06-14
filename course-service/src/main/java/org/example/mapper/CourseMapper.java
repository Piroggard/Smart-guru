package org.example.mapper;

import org.example.dto.CourseRequestDto;
import org.example.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourseDTO (CourseRequestDto courseRequestDTO);
}
