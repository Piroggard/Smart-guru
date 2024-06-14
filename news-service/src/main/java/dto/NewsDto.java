package dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {

    @NotNull(message = "Идентификатор курса не должен быть пустым")
    private Long courseId;

    @NotBlank(message = "Направление не должно быть пустым")
    private String direction;

    @NotBlank(message = "Изображение не должно быть пустым")
    private String image;

    @NotBlank(message = "Заголовок не должен быть пустым")
    private String heading;

    @NotBlank(message = "Описание не должно быть пустым")
    private String description;
}
