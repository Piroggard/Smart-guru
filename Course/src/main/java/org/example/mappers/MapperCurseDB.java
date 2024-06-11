package org.example.mappers;

import org.example.dto.CourseRequestDTO;
import org.example.dto.CourseResponseDTO;
import org.example.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperCurseDB {
    Course toCourseDTO (CourseRequestDTO courseRequestDTO);
}
