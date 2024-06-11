package notifications.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreationEventDto {

    private String name;
    private String url;
    private LocalDate dateStartCourse;
    private LocalDate dateFinishCourse;
    private String organizerName;
    private String organizerEmail;
}
