package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Course;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUpdateRequestDto {

    private UUID id;
    private Course courseId;
    private String country;
    private String city;
    private String street;
    private String house;
    private String district;
}