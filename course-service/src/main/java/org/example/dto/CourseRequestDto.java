package org.example.dto;

import lombok.*;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;

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
    private OrganizerDto organizerId;
    private Boolean certificate;
}
