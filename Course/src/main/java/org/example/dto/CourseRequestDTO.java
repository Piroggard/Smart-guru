package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    private String name;
    private String url;
    private TypeDTO type;
    private int numberSeats;
    private double price;
    private String photoProfile;
    private String description;
    private DirectionDTO directionId;
    private String duration;
    private OrganizerDTO organizerId;
    private boolean certificate;
    private LocalDateTime dateCreate;
    private LocalDateTime dateStartCourse;
    private LocalDateTime dateFinishCourse;
    private boolean delete;
    private LocalDateTime dateDelete;
    private LocalDateTime dateUpdate;
    private StatusDTO statusId;
    private AddressDTO addressId;

}
