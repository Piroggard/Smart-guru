package org.example.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Идентификатор курса не должен быть пустым")
    private Long id;

    @NotBlank(message = "Название не должно быть пустым")
    private String title;

    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @NotBlank(message = "Время не должно быть пустым")
    private LocalDateTime postDate;

    @NotNull(message = "Идентификатор пользователя не должен быть пустым")
    private Long userId;
}
