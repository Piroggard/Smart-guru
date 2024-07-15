package org.example.mapper;

import org.example.dto.PhotosCourseDto;
import org.example.model.PhotosCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotosMapper {

    PhotosCourse toPhotosCourse(PhotosCourseDto photosCourseDto);
}
