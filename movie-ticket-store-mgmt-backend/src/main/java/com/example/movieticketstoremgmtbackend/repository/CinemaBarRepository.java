package com.example.movieticketstoremgmtbackend.repository;

import com.example.movieticketstoremgmtbackend.model.CinemaBarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing and managing CinemaBarEntity objects in the database.
 */
@Repository
public interface CinemaBarRepository extends JpaRepository<CinemaBarEntity, UUID> {

    /**
     * Retrieves all cinema bar entities from the database.
     *
     * @return List of all cinema bar entities.
     */
    List<CinemaBarEntity> findAll();

    /**
     * Retrieves a cinema bar entity by its ID from the database.
     *
     * @param id The ID of the cinema bar entity to retrieve.
     * @return Optional containing the cinema bar entity if found, otherwise empty.
     */
    Optional<CinemaBarEntity> findById(UUID id);

    /**
     * Deletes a cinema bar entity by its ID from the database.
     *
     * @param id The ID of the cinema bar entity to delete.
     */
    void deleteById(UUID id);
}
