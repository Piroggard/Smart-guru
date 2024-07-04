package org.example.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;
import org.example.model.AdressCourse;
import org.example.model.Organizer;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {
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
    private Organizer organizerId;
    private Boolean certificate;
    private LocalDateTime dateCreate;
    private LocalDateTime dateStartCourse;
    private LocalDateTime dateFinishCourse;
    private Boolean delete;
    private LocalDateTime dateDelete;
    private LocalDateTime dateUpdate;
    private AdressCourse addressId;
}
