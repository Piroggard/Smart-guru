package org.example.repository;

import org.example.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;
import java.util.UUID;

public interface RepositoryOrganizer extends JpaRepository<Organizer, UUID> {
}
