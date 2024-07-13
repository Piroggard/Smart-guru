package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.Role;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;

    @NotNull(message = "User's firstname can't be null")
    @Size(max = 128, message = "Firstname can't be more then 128 symbols")
    private String firstname;

    @NotNull(message = "User's lastname can't be null")
    @Size(max = 128, message = "Lastname can't be more then 128 symbols")
    private String lastname;

    @Size(max = 128, message = "Middle name can't be more then 128 symbols")
    private String middleName;

    @Email(message = "Email should be correct")
    @NotNull(message = "User's email can't be null")
    @Size(max = 128, message = "Email can't be more then 128 symbols")
    private String email;

    @NotNull(message = "User's password can't be null")
    @Size(min = 6, max = 1024, message = "Password can't be less then 6 symbols more then 1024 symbols")
    private String password;

    private String photo;

    private Role role;
}
