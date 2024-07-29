package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.exception.IncorrectUserActionException;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.model.enums.Role;
import org.example.repository.UserRepository;
import org.example.util.UserContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserContext userContext;

    @Transactional
    public UUID createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_USER);
        User savedUser = userRepository.save(user);
        log.info("User with email={} was saved in DB", userDto.getEmail());
        return savedUser.getId();
    }

    @Transactional
    public void deleteUser(UUID id, boolean isSoftDelete) {
        checkCorrectnessUserAction(id);
        User user = takeUserOrThrow(id);

        if (isSoftDelete) {
            user.setDelete(true);
            user.setDateDelete(LocalDateTime.now());
            log.info("User with uuid={} was soft deleted from DB", id);
        } else {
            userRepository.deleteById(id);
            log.info("User with uuid={} was deleted from DB", id);
        }
    }

    @Transactional(readOnly = true)
    public UserDto getUser(UUID id) {
        User user = takeUserOrThrow(id);
        log.info("User with uuid={} was taken from DB successfully", id);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto updateUser(UUID id, UserDto userDto) {
        checkCorrectnessUserAction(id);
        User user = takeUserOrThrow(id);
        log.info("User with uuid={} was taken from DB successfully", id);

        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setMiddleName(userDto.getMiddleName());
        user.setPhoto(userDto.getPhoto());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        log.info("User with uuid={} was updated successfully", id);
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAllNonDeleted().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email=%s is not found in DB", email)));
    }

    @Transactional
    protected User registerUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    private void checkCorrectnessUserAction(UUID userId) {
        if (!userContext.getUserId().equals(userId)) {
            throw new IncorrectUserActionException("This operation can do only user himself or user with 'ADMIN' role");
        }
    }

    private User takeUserOrThrow(UUID userId) {
        Optional<User> optUser = userRepository.findByIdNonDeleted(userId);
        return optUser.orElseThrow(
                () -> new EntityNotFoundException(String.format("User is not found")));
    }
}
