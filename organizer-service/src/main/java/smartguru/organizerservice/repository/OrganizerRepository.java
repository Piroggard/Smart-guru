package smartguru.organizerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import smartguru.organizerservice.model.Organizer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {

    @Query("select o from Organizer o where o.email = :email and o.delete = false")
    Optional<Organizer> findByEmail(@Param("email") String email);

    @Query("select o from Organizer o where lower(o.name) like :filter")
    List<Organizer> findAllByNameIgnoreCase(@Param("filter") String filter);

    @Query("select o from Organizer o where o.id = :id and o.delete = false")
    Optional<Organizer> findByIdNonDeleted(@Param("id") UUID id);

    @Query("select o from Organizer o where o.delete = false")
    List<Organizer> findAllNonDeleted();
}
