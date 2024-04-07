package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    @NotNull(message = "Идентификатор курса не должен быть пустым")
    Long courseId;

    @NotBlank(message = "Направление не должно быть пустым")
    String direction;

    @NotBlank(message = "Изображение не должно быть пустым")
    String image;

    @NotBlank(message = "Заголовок не должен быть пустым")
    String heading;

    @NotBlank(message = "Описание не должно быть пустым")
    String description;
}
