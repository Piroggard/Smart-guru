package org.example.repository;

import org.example.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryDirection extends JpaRepository<Direction, Enum> {
}
