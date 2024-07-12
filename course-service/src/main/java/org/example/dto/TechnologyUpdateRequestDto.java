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
public class TechnologyUpdateRequestDto {
    private UUID id;
    private String name;
    private String photo;
    private UUID courseId;
}
