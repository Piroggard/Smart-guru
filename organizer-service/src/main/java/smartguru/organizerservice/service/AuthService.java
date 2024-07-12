package smartguru.organizerservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import smartguru.organizerservice.dto.AuthRequestDto;
import smartguru.organizerservice.dto.AuthResponseDto;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.model.Organizer;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final OrganizerService organizerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto authenticateOrganizer(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword())
        );
        Organizer organizer = organizerService.findByEmail(authRequestDto.getEmail());
        log.info("User with email={} was successfully authenticated", organizer.getEmail());

        return AuthResponseDto.builder()
                .id(organizer.getId())
                .jwt(jwtService.generateToken(organizer))
                .build();
    }

    public AuthResponseDto registerOrganizer(OrganizerDto organizerDto) {
        Organizer organizer = organizerService.registerOrganizer(organizerDto);
        log.info("New organizer with email={} was saved in DB successfully", organizer.getEmail());

        return AuthResponseDto.builder()
                .id(organizer.getId())
                .jwt(jwtService.generateToken(organizer))
                .build();
    }
}
