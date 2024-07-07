package org.example.mapper;

import org.example.dto.CourseRequestUpdateDto;
import org.example.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapperUpdate {
    Course toCourse(CourseRequestUpdateDto courseRequestUpdateDto);
}
