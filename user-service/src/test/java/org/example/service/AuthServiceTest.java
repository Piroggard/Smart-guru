package org.example.service;

import org.example.dto.AuthRequestDto;
import org.example.dto.AuthResponseDto;
import org.example.dto.UserDto;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String JWT = "testJwt";

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private User user;
    private AuthResponseDto expectedResponse;

    @BeforeEach
    void initData() {
        user = User.builder()
                .id(ID)
                .firstname("Firstname")
                .lastname("Lastname")
                .email("test@mail.ru")
                .password("Password")
                .build();
        expectedResponse = AuthResponseDto.builder()
                .id(ID)
                .jwt(JWT)
                .build();
    }

    @Test
    void testRegisterUser() {
        UserDto userDto = UserDto.builder()
                .firstname("Firstname")
                .lastname("Lastname")
                .email("test@mail.ru")
                .password("Password")
                .build();
        when(userService.registerUser(userDto)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(JWT);

        AuthResponseDto actualResponse = authService.registerUser(userDto);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testAuthenticateUser() {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test@mail.ru")
                .password("Password")
                .build();
        when(userService.findByEmail(requestDto.getEmail())).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(JWT);

        AuthResponseDto actualResponse = authService.authenticateUser(requestDto);
        assertEquals(expectedResponse, actualResponse);
    }
}
