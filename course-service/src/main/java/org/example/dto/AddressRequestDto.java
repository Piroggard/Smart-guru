package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Course;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {

    private Course courseId;
    private String country;
    private String city;
    private String street;
    private String house;
    private String district;
}
