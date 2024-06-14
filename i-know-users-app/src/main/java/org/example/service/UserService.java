package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDto;
import org.example.mapper.UserMapper;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UUID createUser(UserDto userDto) {
        User savedUser = userRepository.save(userMapper.toEntity(userDto));
        log.info("User with email={} was saved in DB", userDto.getEmail());
        return savedUser.getId();
    }

    @Transactional
    public void deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User with uuid={} was deleted from DB", id);
        } else {
            log.info("User with uuid={} was not found in DB", id);
        }
    }

    @Transactional(readOnly = true)
    public UserDto getUser(UUID id) {
        User user = takeUserOrThrow(id);
        log.info("User with uuid={} was taken from DB successfully", id);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = takeUserOrThrow(userDto.getId());
        log.info("User with uuid={} was taken from DB successfully", userDto.getId());

        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setMiddleName(userDto.getMiddleName());
        user.setPhoto(userDto.getPhoto());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        log.info("User with uuid={} was updated successfully", userDto.getId());
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    private User takeUserOrThrow(UUID userId) {
        Optional<User> optUser = userRepository.findById(userId);
        return optUser.orElseThrow(
                () -> new EntityNotFoundException(String.format("User is not found")));
    }
}
