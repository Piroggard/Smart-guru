package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private UUID courseId;
    private String country;
    private String city;
    private String street;
    private String house;
    private String district;
    private Boolean delete;
}
