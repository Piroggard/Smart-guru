package smartguru.organizerservice.contoller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import smartguru.organizerservice.controller.OrganizerController;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.model.enums.Role;
import smartguru.organizerservice.service.JwtService;
import smartguru.organizerservice.service.OrganizerService;
import smartguru.organizerservice.util.UserContext;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrganizerController.class)
public class OrganizerControllerTest {

    private static final UUID ID = UUID.randomUUID();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private OrganizerService organizerService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserContext userContext;

    private OrganizerDto organizerDto;

    @BeforeEach
    void initData() {
        organizerDto = OrganizerDto.builder()
                .id(ID)
                .email("test@mail.ru")
                .name("Name")
                .password("Password")
                .build();
    }

    @Test
    void testGetOrganizer_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(get("/api/v1/organizers/" + ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetOrganizer() throws Exception {
        when(organizerService.getOrganizer(ID)).thenReturn(organizerDto);

        mockMvc.perform(get("/api/v1/organizers/" + ID)
                        .with(user("test@mail.ru").authorities(Role.ROLE_ORGANIZER)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(content().json("""
                        {"name": "Name", "email": "test@mail.ru", "password": "Password"}
                        """));
    }

    @Test
    void testCreateOrganizer_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(post("/api/v1/organizers")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testCreateOrganizer() throws Exception {
        when(organizerService.createOrganizer(organizerDto)).thenReturn(ID);

        mockMvc.perform(post("/api/v1/organizers")
                        .with(csrf())
                        .with(user("test@mail.ru").authorities(Role.ROLE_ORGANIZER))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {"name": "Name", "email": "test@mail.ru", "password": "Password"}
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteOrganizer_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(delete("/api/v1/organizers/" + ID + "?soft-delete=false")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDeleteOrganizer() throws Exception {
        mockMvc.perform(delete("/api/v1/organizers/" + ID + "?soft-delete=false")
                        .with(user("test").authorities(Role.ROLE_ORGANIZER))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateOrganizer_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(put("/api/v1/organizers/" + ID)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testUpdateOrganizer() throws Exception {
        when(organizerService.updateOrganizer(ID, organizerDto)).thenReturn(organizerDto);

        mockMvc.perform(put("/api/v1/organizers/" + ID)
                        .with(user("test@mail.ru").authorities(Role.ROLE_ORGANIZER))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"firstname": "Test", "lastname": "Test", "email": "test@mail.ru", "password": "Password"}
                                """))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllOrganizers_ThrowUnauthorizedException() throws Exception {
        mockMvc.perform(get("/api/v1/organizers"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetAllOrganizers() throws Exception {
        when(organizerService.getAllOrganizers("name")).thenReturn(List.of(organizerDto));

        mockMvc.perform(get("/api/v1/organizers?filter=name")
                        .with(user("test@mail.ru").authorities(Role.ROLE_ORGANIZER)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"name": "Name", "email": "test@mail.ru", "password": "Password"}]
                        """));
    }
}
