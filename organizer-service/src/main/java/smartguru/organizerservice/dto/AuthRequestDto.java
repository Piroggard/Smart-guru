package smartguru.organizerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDto {

    @Email(message = "Email should be correct")
    @NotNull(message = "Email can't be null")
    @Size(max = 128, message = "Email can't be more then 128 symbols")
    private String email;

    @NotNull(message = "Password can't be null")
    @Size(max = 1024, message = "Password can't be more then 128 symbols")
    private String password;
}
