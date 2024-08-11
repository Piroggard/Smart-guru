package org.example.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDto {

    private UUID id;
    private String title;
    private String description;
    private UUID courseId;
    private UUID userId;
    private int rating;
    private boolean deleted;
    private LocalDateTime dateModeration;
    private LocalDateTime datePublication;
    private LocalDateTime dateDelete;

}