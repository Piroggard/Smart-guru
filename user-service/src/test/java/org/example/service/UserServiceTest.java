package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.UserDto;
import org.example.exception.IncorrectUserActionException;
import org.example.mapper.UserMapperImpl;
import org.example.model.User;
import org.example.model.enums.Role;
import org.example.repository.UserRepository;
import org.example.util.UserContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final UUID ID = UUID.randomUUID();

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserContext userContext;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Spy
    private UserMapperImpl userMapper;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void initData() {
        userDto = UserDto.builder()
                .email("test@mail.ru")
                .firstname("Firstname")
                .lastname("Lastname")
                .password("Password")
                .build();
        user = User.builder()
                .id(ID)
                .email("test@mail.ru")
                .firstname("Firstname")
                .lastname("Lastname")
                .password("Password")
                .role(Role.ROLE_USER)
                .build();
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any())).thenReturn(user);
        UUID actualId = userService.createUser(userDto);
        assertEquals(ID, actualId);
    }

    @Test
    void testDeleteUser_throwIncorrectActionException() {
        when(userContext.getUserId()).thenReturn(UUID.randomUUID());
        assertThrows(IncorrectUserActionException.class, () -> userService.deleteUser(ID, true));
    }

    @Test
    void testDeleteUser_throwEntityNotFoundException() {
        when(userContext.getUserId()).thenReturn(ID);
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(ID, true));
    }

    @Test
    void testDeleteUser_softDelete() {
        when(userContext.getUserId()).thenReturn(ID);
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.of(user));
        userService.deleteUser(ID, true);

        boolean actualResult = user.isDelete();
        assertEquals(true, actualResult);
    }

    @Test
    void testDeleteUser() {
        when(userContext.getUserId()).thenReturn(ID);
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.of(user));
        userService.deleteUser(ID, false);

        verify(userRepository).deleteById(ID);
    }

    @Test
    void testGetUser_throwEntityNotFoundException() {
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->  userService.getUser(ID));
    }

    @Test
    void testGetUser() {
        userDto.setId(ID);
        userDto.setRole(Role.ROLE_USER);
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.of(user));

        UserDto actualDto = userService.getUser(ID);
        assertEquals(userDto, actualDto);
    }

    @Test
    void testUpdateUser_throwIncorrectActionException() {
        when(userContext.getUserId()).thenReturn(UUID.randomUUID());
        assertThrows(IncorrectUserActionException.class, () -> userService.updateUser(ID, userDto));
    }

    @Test
    void testUpdateUser_throwEntityNotFoundException() {
        when(userContext.getUserId()).thenReturn(ID);
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(ID, userDto));
    }

    @Test
    void testUpdateUser() {
        userDto.setId(ID);
        userDto.setRole(Role.ROLE_USER);
        userDto.setEmail("newTest@mail.ru");
        userDto.setFirstname("NewFirstname");
        when(userContext.getUserId()).thenReturn(ID);
        when(userRepository.findByIdNonDeleted(ID)).thenReturn(Optional.of(user));

        UserDto actualDto = userService.updateUser(ID, userDto);
        actualDto.setPassword(userDto.getPassword());
        assertEquals(userDto, actualDto);
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAllNonDeleted()).thenReturn(List.of(user));

        userDto.setId(ID);
        userDto.setRole(Role.ROLE_USER);
        List<UserDto> expectedList = List.of(userDto);
        List<UserDto> actualList = userService.getAllUsers();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testFindByEmail_throwUsernameNotFoundException() {
        when(userRepository.findByEmail("test@mail.ru")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail(user.getEmail()));
    }

    @Test
    void testFindByEmail() {
        when(userRepository.findByEmail("test@mail.ru")).thenReturn(Optional.of(user));
        User actualUser = userService.findByEmail(user.getEmail());
        assertEquals(user, actualUser);
    }

    @Test
    void testRegisterUser() {
        when(userRepository.save(any())).thenReturn(user);
        User actualUser = userService.registerUser(userDto);
        assertEquals(user, actualUser);
    }
}
