package smartguru.organizerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartguru.organizerservice.dto.AuthRequestDto;
import smartguru.organizerservice.dto.AuthResponseDto;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Service", description = "It's a service for authentication and authorization organizers")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register organizer", description = "This is an open method to everyone",
            method = "POST")
    @PostMapping("/registration/organizer")
    public AuthResponseDto registerOrganizer(@Valid @RequestBody OrganizerDto organizerDto) {
        log.info("Received request to register organizer with email={}", organizerDto.getEmail());
        return authService.registerOrganizer(organizerDto);
    }

    @Operation(summary = "Authenticate organizer", description = "This is an open method to everyone for user log in",
            method = "POST")
    @PostMapping("/login/organizer")
    public AuthResponseDto authenticateOrganizer(@Valid @RequestBody AuthRequestDto authRequestDto) {
        log.info("Received request to authenticate organizer with email={}", authRequestDto.getEmail());
        return authService.authenticateOrganizer(authRequestDto);
    }
}
