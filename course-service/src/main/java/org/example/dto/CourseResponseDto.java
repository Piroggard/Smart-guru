package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;
import org.example.model.Technology;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDto {
    private UUID id;
    private String name;
    private String url;
    private TypeEnum type;
    private Long numberSeats;
    private Long price;
    private String photoProfile;
    private String description;
    private DirectionEnum direction;
    private String duration;
    private StatusEnum status;
    private String whatLearn;
    private OrganizerDto organizerId;
    private Boolean certificate;
    private Boolean delete;
    private AddressResponseDto addressRequestDto;
    private List<PhotosResponseDto> photosCourseDtos;
    private TechnologyResponseDto technology;
}
