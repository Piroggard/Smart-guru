package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdarionDto {

    CourseUpdateDto course;
    AddressUpdateRequestDto address;
    List<PhotosUpdateCourseDto> photos;
    TechnologyUpdateRequestDto technology;
}
