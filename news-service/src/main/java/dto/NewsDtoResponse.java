package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDtoResponse {
    private Long courseId;
    private String direction;
    private String image;
    private String heading;
    private String description;
    private LocalDateTime publicationDate;
}
