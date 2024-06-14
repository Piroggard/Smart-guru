package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "User Service", description = "It's a service for working with users")
@ApiResponses(value = {@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
        @ApiResponse(responseCode = "500", description = "INITIAL SERVER ERROR")})
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create user", description = "Method can be used by the admin",
            method = "POST")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to create user with email={}", userDto.getEmail());
        return userService.createUser(userDto);
    }

    @Operation(summary = "Get user by id", description = "User must be exist",
            method = "GET")
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable UUID id) {
        log.info("Received request to get user with uuid={}", id);
        return userService.getUser(id);
    }

    @Operation(summary = "Delete user by id", description = "Method can be used by the user himself or by the admin",
            method = "DELETE")
    @PreAuthorize("hasRole('ADMIN') or @userAuthFacade.isIdMatch(#id)")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        log.info("Received request to delete user with uuid={}", id);
        userService.deleteUser(id);
    }

    @Operation(summary = "Update user by id", description = "Method can be used by the user himself or by the admin",
            method = "PUT")
    @PreAuthorize("hasRole('ADMIN') or @userAuthFacade.isIdMatch(#id)")
    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to update user with uuid={}", userDto.getId());
        return userService.updateUser(userDto);
    }

    @Operation(summary = "Get all users", description = "Return list of users or empty list",
            method = "GET")
    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Received request to get all users");
        return userService.getAllUsers();
    }
}
