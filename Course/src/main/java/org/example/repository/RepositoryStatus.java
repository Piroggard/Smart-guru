package org.example.repository;


import org.example.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryStatus extends JpaRepository<Status, Enum> {
}
