package com.example.movieticketstoremgmtbackend.repository;

import com.example.movieticketstoremgmtbackend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Repository interface for accessing and managing UserEntity objects in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * Retrieves all users from the database.
     * @return List of all users.
     */
    List<UserEntity> findAll();

    /**
     * Retrieves a user entity by its ID from the database.
     * @param id The ID of the user entity to retrieve.
     * @return Optional containing the user entity if found, otherwise empty.
     */
    Optional<UserEntity> findById(UUID id);

    /**
     * Retrieves a user entity by its email address from the database.
     * @param email The email address of the user entity to retrieve.
     * @return Optional containing the user entity if found, otherwise empty.
     */
    Optional<UserEntity> findByEmail(String email);
}
