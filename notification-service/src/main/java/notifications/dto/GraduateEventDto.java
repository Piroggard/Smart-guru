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
public class GraduateEventDto {

    private String firstname;
    private String lastname;
    private String email;
    private String courseName;
    private String courseDirection;
    private LocalDate dateFinishCourse;
}
