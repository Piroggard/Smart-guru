package org.example.controller;

import org.example.dto.UserDto;
import org.example.model.enums.Role;
import org.example.service.JwtService;
import org.example.service.UserService;
import org.example.util.UserContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final UUID ID = UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserContext userContext;

    private UserDto userDto;

    @BeforeEach
    void initData() {
        userDto = UserDto.builder()
                .id(ID)
                .firstname("Test")
                .lastname("Test")
                .email("test@mail.ru")
                .password("Password")
                .build();
    }

    @Test
    void testGetUser_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(get("/api/v1/users/" + ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetUser() throws Exception {
        when(userService.getUser(ID)).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/users/" + ID)
                        .with(user("test@mail.ru").authorities(Role.ROLE_USER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(content().json("""
                        {"firstname": "Test", "lastname": "Test", "email": "test@mail.ru", "password": "Password"}
                        """));
    }

    @Test
    void testCreateUser_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(userDto)).thenReturn(ID);

        mockMvc.perform(post("/api/v1/users")
                        .with(csrf())
                        .with(user("test@mail.ru").authorities(Role.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {"firstname": "Test", "lastname": "Test", "email": "test@mail.ru", "password": "Password"}
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteUser_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(delete("/api/v1/users/" + ID + "?soft-delete=false")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/v1/users/" + ID + "?soft-delete=false")
                        .with(user("test").authorities(Role.ROLE_USER))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(put("/api/v1/users/" + ID)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(ID, userDto)).thenReturn(userDto);

        mockMvc.perform(put("/api/v1/users/" + ID)
                        .with(user("test@mail.ru").authorities(Role.ROLE_USER))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstname": "Test", "lastname": "Test", "email": "test@mail.ru", "password": "Password"}
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllUsers_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(userDto));

        mockMvc.perform(get("/api/v1/users")
                        .with(user("test@mail.ru").authorities(Role.ROLE_USER)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"firstname": "Test", "lastname": "Test", "email": "test@mail.ru", "password": "Password"}]
                        """));
    }
}
