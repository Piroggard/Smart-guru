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
    CourseUpdateDto courseUpdateDto;
    AddressRequestDto addressRequestDto;
    List<PhotosCourseDto> photos;
    TechnologyRequestDto technology;
}
