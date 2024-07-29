package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AuthRequestDto;
import org.example.dto.AuthResponseDto;
import org.example.dto.UserDto;
import org.example.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto registerUser(UserDto userDto) {
        User user = userService.registerUser(userDto);
        log.info("New user with email={} was saved in DB successfully", user.getEmail());

        return AuthResponseDto.builder()
                .id(user.getId())
                .jwt(jwtService.generateToken(user))
                .build();
    }

    public AuthResponseDto authenticateUser(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword())
        );
        User user = userService.findByEmail(authRequestDto.getEmail());
        log.info("User with email={} was successfully authenticated", user.getEmail());

        return AuthResponseDto.builder()
                .id(user.getId())
                .jwt(jwtService.generateToken(user))
                .build();
    }
}
