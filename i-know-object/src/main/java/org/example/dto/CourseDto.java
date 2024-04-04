package org.example.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDto {
    String name;
    String url;
    String type;
    AddressDto address = new AddressDto();
    long numberSeats;
    long price;
    String photo;
    List<PhotosDto> photosCourse = new ArrayList<>();
    List <ReviewDto> reviews = new ArrayList<>();
}
