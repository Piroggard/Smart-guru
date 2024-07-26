package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "CourseId should not be null")
    private UUID courseId;

    @NotNull(message = "Direction should not be null")
    private DirectionEnum direction;

    @Size(max = 256, message = "Image should not be more then 256 characters")
    private String image;

    @NotEmpty(message = "Title should not be empty")
    @Size(max = 128, message = "Title should not be more then 128 characters")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @Size(max = 4096, message = "Description should not be more then 4096 characters")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datePublication;
}
