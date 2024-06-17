package org.example.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime postDate;
    private Long courseId;
    private Long userId;
}