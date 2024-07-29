package smartguru.organizerservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.exception.IncorrectUserActionException;
import smartguru.organizerservice.mapper.OrganizerMapper;
import smartguru.organizerservice.model.Organizer;
import smartguru.organizerservice.model.enums.Role;
import smartguru.organizerservice.repository.OrganizerRepository;
import smartguru.organizerservice.util.UserContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizerMapper organizerMapper;
    private final UserContext userContext;

    @Transactional
    public UUID createOrganizer(OrganizerDto organizerDto) {
        Organizer organizer = organizerMapper.toEntity(organizerDto);
        organizer.setPassword(passwordEncoder.encode(organizerDto.getPassword()));
        Organizer savedOrganizer = organizerRepository.save(organizer);
        log.info("New organizer with email={} was saved in DB successfully", organizerDto.getEmail());
        return savedOrganizer.getId();
    }

    @Transactional(readOnly = true)
    public OrganizerDto getOrganizer(UUID id) {
        Organizer organizer = takeOrganizerOrThrow(id);
        log.info("Organizer with uuid={} was taken from DB successfully", id);
        return organizerMapper.toDto(organizer);
    }

    @Transactional
    public OrganizerDto updateOrganizer(UUID id, OrganizerDto organizerDto) {
        checkCorrectnessUserAction(id);
        Organizer organizer = takeOrganizerOrThrow(id);

        organizer.setName(organizerDto.getName());
        organizer.setEmail(organizerDto.getEmail());
        organizer.setPassword(passwordEncoder.encode(organizerDto.getPassword()));

        log.info("Organizer with uuid={} was updated successfully", id);
        return organizerMapper.toDto(organizer);
    }

    @Transactional
    public void deleteOrganizer(UUID id, boolean isSoftDelete) {
        checkCorrectnessUserAction(id);
        Organizer organizer = takeOrganizerOrThrow(id);

        if (isSoftDelete) {
            organizer.setDelete(true);
            organizer.setDateDelete(LocalDateTime.now());
            log.info("Organizer with uuid={} was soft deleted from DB", id);
        } else {
            organizerRepository.deleteById(id);
            log.info("Organizer with uuid={} was deleted from DB", id);
        }
    }

    @Transactional(readOnly = true)
    public List<OrganizerDto> getAllOrganizers(String filter) {
        if (filter != null && !filter.isBlank()) {
            return organizerRepository.findAllByNameIgnoreCase("%" + filter + "%").stream()
                    .map(organizerMapper::toDto)
                    .toList();
        }
        return organizerRepository.findAllNonDeleted().stream()
                .map(organizerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    protected Organizer findByEmail(String email) {
        return organizerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Organizer with email=%s is not found in DB", email)));
    }

    @Transactional
    protected Organizer registerOrganizer(OrganizerDto organizerDto) {
        Organizer organizer = organizerMapper.toEntity(organizerDto);
        organizer.setPassword(passwordEncoder.encode(organizerDto.getPassword()));
        organizer.setRole(Role.ROLE_ORGANIZER);
        return organizerRepository.save(organizer);
    }

    private void checkCorrectnessUserAction(UUID organizerId) {
        if (!userContext.getUserId().equals(organizerId)) {
            throw new IncorrectUserActionException("This operation can do only user himself or user with 'ADMIN' role");
        }
    }

    private Organizer takeOrganizerOrThrow(UUID organizerId) {
        Optional<Organizer> optOrganizer = organizerRepository.findByIdNonDeleted(organizerId);
        return optOrganizer.orElseThrow(
                () -> new EntityNotFoundException(String.format("Organizer is not found")));
    }
}
