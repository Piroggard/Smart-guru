package smartguru.organizerservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import smartguru.organizerservice.model.enums.Role;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizerDto {

    private UUID id;

    @NotNull(message = "Name can't be null")
    @Size(max = 128, message = "Name can't be more then 128 symbols")
    private String name;

    @NotNull(message = "Email can't be null")
    @Email(message = "Email should be correct")
    @Size(max = 128, message = "Email can't be more then 128 symbols")
    private String email;

    @NotNull(message = "Password can't be null")
    @Size(min = 6, max = 1024, message = "Password can't be less then 6 symbols and more then 1024 symbols")
    private String password;

    private Role role;
}
