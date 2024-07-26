package org.example.controller;

import org.example.dto.AuthRequestDto;
import org.example.dto.AuthResponseDto;
import org.example.dto.UserDto;
import org.example.model.enums.Role;
import org.example.service.AuthService;
import org.example.service.JwtService;
import org.example.service.UserService;
import org.example.util.UserContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserContext userContext;

    private UserDto userDto;
    private AuthRequestDto authRequestDto;
    private AuthResponseDto responseDto;

    @BeforeEach
    void initData() {
        userDto = UserDto.builder()
                .firstname("Test")
                .lastname("Test")
                .email("test@mail.ru")
                .password("Password")
                .build();
        authRequestDto = AuthRequestDto.builder()
                .email("test@mail.ru")
                .password("Password")
                .build();
        responseDto = AuthResponseDto.builder()
                .id(UUID.randomUUID())
                .jwt("test_token")
                .build();
    }

    @Test
    void testRegisterUser() throws Exception {
        Mockito.when(authService.registerUser(userDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/auth/registration/user")
                        .with(csrf())
                        .with(user("test@mail.ru").authorities(Role.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {"firstname": "Test", "lastname": "Test", "email": "test@mail.ru", "password": "Password"}
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"jwt": "test_token"}
                        """));
    }

    @Test
    void testAuthenticateUser() throws Exception {
        Mockito.when(authService.authenticateUser(authRequestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/auth/login/user")
                        .with(csrf())
                        .with(user("test@mail.ru").authorities(Role.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"email": "test@mail.ru", "password": "Password"}
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"jwt": "test_token"}
                        """));
    }
}
