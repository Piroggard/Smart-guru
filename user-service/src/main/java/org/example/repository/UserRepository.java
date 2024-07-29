package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.email = :email and u.delete = false")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u where u.id = :id and u.delete = false")
    Optional<User> findByIdNonDeleted(@Param("id") UUID id);

    @Query("select u from User u where u.delete = false")
    List<User> findAllNonDeleted();
}
