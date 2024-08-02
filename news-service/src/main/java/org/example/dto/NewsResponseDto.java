package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enam.DirectionEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDto {

    private UUID id;
    private UUID courseId;
    private DirectionEnum direction;
    private String directionDescription;
    private String image;
    private String title;
    private String description;
    private LocalDateTime datePublication;
}