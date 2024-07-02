package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AuthRequestDto;
import org.example.dto.AuthResponseDto;
import org.example.dto.UserDto;
import org.example.service.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Service", description = "It's a service for authentication and authorization users")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register user", description = "This is an open method to everyone",
            method = "POST")
    @PostMapping("/registration/user")
    public AuthResponseDto registerUser(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to register user with email={}", userDto.getEmail());
        return authService.registerUser(userDto);
    }

    @Operation(summary = "Authenticate user", description = "This is an open method to everyone for user log in",
            method = "POST")
    @PostMapping("/login/user")
    public AuthResponseDto authenticateUser(@Valid @RequestBody AuthRequestDto authRequestDto) {
        log.info("Received request to authenticate user with email={}", authRequestDto.getEmail());
        return authService.authenticateUser(authRequestDto);
    }
}
