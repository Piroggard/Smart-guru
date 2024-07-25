package org.example.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class NewsUpdateDto {

    private UUID id;

    private UUID courseId;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Direction should not be empty")
    @Size(max = 128, message = "Direction should not be more then 128 characters")
    private DirectionEnum direction;

    @Size(max = 256, message = "Image should not be more then 256 characters")
    private String image;

    @NotEmpty(message = "Title should not be empty")
    @Size(max = 128, message = "Title should not be more then 128 characters")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @Size(max = 4096, message = "Description should not be more then 4096 characters")
    private String description;

    private LocalDateTime datePublication;
}
