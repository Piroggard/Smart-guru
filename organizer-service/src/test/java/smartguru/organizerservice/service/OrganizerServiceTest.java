package smartguru.organizerservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.exception.IncorrectUserActionException;
import smartguru.organizerservice.mapper.OrganizerMapperImpl;
import smartguru.organizerservice.model.Organizer;
import smartguru.organizerservice.repository.OrganizerRepository;
import smartguru.organizerservice.util.UserContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizerServiceTest {

    private static final UUID ID = UUID.randomUUID();

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private UserContext userContext;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Spy
    private OrganizerMapperImpl organizerMapper;

    @InjectMocks
    private OrganizerService organizerService;

    private Organizer organizer;
    private OrganizerDto organizerDto;

    @BeforeEach
    void initData() {
        organizer = Organizer.builder()
                .id(ID)
                .name("Name")
                .email("test@mail.ru")
                .password("Password")
                .build();
        organizerDto = OrganizerDto.builder()
                .name("Name")
                .email("test@mail.ru")
                .password("Password")
                .build();
    }

    @Test
    void testCreateOrganizer() {
        when(organizerRepository.save(any())).thenReturn(organizer);
        UUID actualId = organizerService.createOrganizer(organizerDto);
        assertEquals(ID, actualId);
    }

    @Test
    void testGetOrganizer_ThrowEntityNotFoundException() {
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->    organizerService.getOrganizer(ID));
    }

    @Test
    void testGetOrganizer() {
        organizerDto.setId(ID);
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.ofNullable(organizer));
        OrganizerDto actualOrganizerDto = organizerService.getOrganizer(ID);
        assertEquals(organizerDto, actualOrganizerDto);
    }

    @Test
    void testUpdateOrganizer_ThrowIncorrectActionException() {
        when(userContext.getUserId()).thenReturn(UUID.randomUUID());
        assertThrows(IncorrectUserActionException.class, () -> organizerService.updateOrganizer(ID, organizerDto));
    }

    @Test
    void testUpdateOrganizer_ThrowEntityNotFoundException() {
        when(userContext.getUserId()).thenReturn(ID);
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> organizerService.updateOrganizer(ID, organizerDto));
    }

    @Test
    void testUpdateOrganizer() {
        organizerDto.setId(ID);
        organizerDto.setName("NewName");
        organizerDto.setEmail("newTest@mail.ru");
        when(userContext.getUserId()).thenReturn(ID);
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.ofNullable(organizer));

        OrganizerDto actualDto = organizerService.updateOrganizer(ID, organizerDto);
        organizerDto.setPassword(actualDto.getPassword());
        assertEquals(organizerDto, actualDto);
    }

    @Test
    void testDeleteOrganizer_ThrowIncorrectActionException() {
        when(userContext.getUserId()).thenReturn(UUID.randomUUID());
        assertThrows(IncorrectUserActionException.class, () -> organizerService.deleteOrganizer(ID, true));
    }

    @Test
    void testDeleteOrganizer_ThrowEntityNotFoundException() {
        when(userContext.getUserId()).thenReturn(ID);
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> organizerService.deleteOrganizer(ID, true));
    }

    @Test
    void testDeleteOrganizer_softDelete() {
        when(userContext.getUserId()).thenReturn(ID);
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.ofNullable(organizer));
        organizerService.deleteOrganizer(ID, true);

        boolean actualResult = organizer.isDelete();
        assertEquals(true, actualResult);
    }

    @Test
    void testDeleteOrganizer() {
        when(userContext.getUserId()).thenReturn(ID);
        when(organizerRepository.findByIdNonDeleted(ID)).thenReturn(Optional.ofNullable(organizer));
        organizerService.deleteOrganizer(ID, false);

        verify(organizerRepository).deleteById(ID);
    }

    @Test
    void testGetAllOrganizers() {
        when(organizerRepository.findAllNonDeleted()).thenReturn(List.of(organizer));
        organizerDto.setId(ID);

        List<OrganizerDto> expectedList = List.of(organizerDto);
        List<OrganizerDto> actualList = organizerService.getAllOrganizers("");
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetAllOrganizers_WithFilter() {
        String filter = "%name%";
        when(organizerRepository.findAllByNameIgnoreCase(filter)).thenReturn(List.of(organizer));
        organizerDto.setId(ID);

        List<OrganizerDto> expectedList = List.of(organizerDto);
        List<OrganizerDto> actualList = organizerService.getAllOrganizers("name");
        assertEquals(expectedList, actualList);
    }

    @Test
    void testFindByEmail_ThrowUsernameNotFoundException() {
        String email = "test@mail.ru";
        when(organizerRepository.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> organizerService.findByEmail(email));
    }

    @Test
    void testFindByEmail() {
        String email = "test@mail.ru";
        when(organizerRepository.findByEmail(email)).thenReturn(Optional.ofNullable(organizer));

        Organizer actualOrganizer = organizerService.findByEmail(email);
        assertEquals(organizer, actualOrganizer);
    }

    @Test
    void testRegisterOrganizer() {
        when(organizerRepository.save(any())).thenReturn(organizer);
        Organizer actualOrganizer = organizerService.registerOrganizer(organizerDto);
        assertEquals(organizer, actualOrganizer);
    }
}
