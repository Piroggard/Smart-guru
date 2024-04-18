package org.example.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
