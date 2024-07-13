package smartguru.organizerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import smartguru.organizerservice.dto.OrganizerDto;
import smartguru.organizerservice.service.OrganizerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizers")
@Slf4j
@RequiredArgsConstructor
@Validated
@Tag(name = "Organizer Service", description = "It's a service for working with organizers")
@ApiResponses(value = {@ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
        @ApiResponse(responseCode = "500", description = "INITIAL SERVER ERROR")})
@SecurityRequirement(name = "bearerAuth")
public class OrganizerController {

    private final OrganizerService organizerService;

    @Operation(summary = "Create organizer", description = "Method can be used by the admin",
            method = "POST")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createOrganizer(@RequestBody @Valid OrganizerDto organizerDto) {
        log.info("Received request to create new organizer with email={}", organizerDto.getEmail());
        return organizerService.createOrganizer(organizerDto);
    }

    @Operation(summary = "Get organizer", description = "Organizer must be exist",
            method = "GET")
    @GetMapping("/{id}")
    public OrganizerDto getOrganizer(@PathVariable UUID id) {
        log.info("Received request to get organizer with uuid={}", id);
        return organizerService.getOrganizer(id);
    }

    @Operation(summary = "Delete organizer by id",
            description = "Method can be used by the organizer himself or by the admin. Uri has required param 'soft-delete'",
            method = "DELETE")
    @DeleteMapping("/{id}")
    public void deleteOrganizer(@PathVariable UUID id, @RequestParam("soft-delete") boolean isSoftDelete) {
        log.info("Received request to delete organizer with uuid={}", id);
        organizerService.deleteOrganizer(id, isSoftDelete);
    }

    @Operation(summary = "Update organizer by id",
            description = "Method can be used by the organizer himself or by the admin",
            method = "PUT")
    @PutMapping("/{id}")
    public OrganizerDto updateOrganizer(@Valid @PathVariable UUID id, @RequestBody OrganizerDto userDto) {
        log.info("Received request to update organizer with uuid={}", id);
        return organizerService.updateOrganizer(id, userDto);
    }

    @Operation(summary = "Get all organizers",
            description = "Return list of organizers or empty list. Uri has no-required param 'filter'",
            method = "GET")
    @GetMapping
    public List<OrganizerDto> getAllOrganizer(@RequestParam(name = "filter", required = false) String filter) {
        log.info("Received request to get all organizers");
        return organizerService.getAllOrganizers(filter);
    }
}
