package org.example.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {
    private String name;
    private String url;
    private TypeDto type;
    private int numberSeats;
    private double price;
    private String photoProfile;
    private String description;
    private DirectionDto directionId;
    private String duration;
    private OrganizerDto organizerId;
    private boolean certificate;
    private LocalDateTime dateCreate;
    private LocalDateTime dateStartCourse;
    private LocalDateTime dateFinishCourse;
    private boolean delete;
    private LocalDateTime dateDelete;
    private LocalDateTime dateUpdate;
    private StatusDto statusId;
    private AddressDto addressId;

}
