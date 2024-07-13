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
public class CourseCreationDTO {

    CourseRequestDto course;
    AddressRequestDto address;
    List<PhotosCourseDto> photos;
    TechnologyRequestDto technology;
}
