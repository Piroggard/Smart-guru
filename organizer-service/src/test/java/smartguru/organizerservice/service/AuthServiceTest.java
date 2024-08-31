package smartguru.organizerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import smartguru.organizerservice.dto.AuthRequestDto;
import smartguru.organizerservice.dto.AuthResponseDto;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.model.Organizer;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    private static final UUID ID = UUID.randomUUID();
    private static final String JWT = "testJwt";

    @Mock
    private OrganizerService organizerService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private Organizer organizer;
    private AuthResponseDto expectedResponse;

    @BeforeEach
    void initData() {
        organizer = Organizer.builder()
                .id(ID)
                .name("Name")
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
        OrganizerDto organizerDto = OrganizerDto.builder()
                .name("Name")
                .email("test@mail.ru")
                .password("Password")
                .build();
        when(organizerService.registerOrganizer(organizerDto)).thenReturn(organizer);
        when(jwtService.generateToken(organizer)).thenReturn(JWT);

        AuthResponseDto actualResponse = authService.registerOrganizer(organizerDto);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testAuthenticateUser() {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("test@mail.ru")
                .password("Password")
                .build();
        when(organizerService.findByEmail(requestDto.getEmail())).thenReturn(organizer);
        when(jwtService.generateToken(organizer)).thenReturn(JWT);

        AuthResponseDto actualResponse = authService.authenticateOrganizer(requestDto);
        assertEquals(expectedResponse, actualResponse);
    }
}
