package com.example.movieticketstoremgmtbackend.repository;

import com.example.movieticketstoremgmtbackend.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Repository interface for accessing and managing MovieEntity objects in the database.
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    /**
     * Retrieves all movies with ticket price greater than the specified value from the database.
     * @param ticketPrice The minimum ticket price for movies to retrieve.
     * @return List of movies with ticket price greater than the specified value.
     */
    List<MovieEntity> findAllByTicketPriceGreaterThan(double ticketPrice);

    /**
     * Retrieves all movies from the database.
     * @return List of all movies.
     */
    List<MovieEntity> findAll();

    /**
     * Retrieves a movie entity by its ID from the database.
     * @param id The ID of the movie entity to retrieve.
     * @return Optional containing the movie entity if found, otherwise empty.
     */
    Optional<MovieEntity> findById(UUID id);

    /**
     * Deletes a movie entity by its ID from the database.
     * @param id The ID of the movie entity to delete.
     */
    void deleteById(UUID id);
}
