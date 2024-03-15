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
    Long courseId;
    String direction;
    String image;
    String heading;
    String description;
    LocalDateTime publicationDate;
}
