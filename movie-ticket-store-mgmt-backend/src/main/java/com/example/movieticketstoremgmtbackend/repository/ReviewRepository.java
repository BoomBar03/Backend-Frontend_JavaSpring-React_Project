package com.example.movieticketstoremgmtbackend.repository;

import com.example.movieticketstoremgmtbackend.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Repository interface for accessing and managing ReviewEntity objects in the database.
 */
@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    /**
     * Retrieves all reviews from the database.
     * @return List of all reviews.
     */
    List<ReviewEntity> findAll();

    /**
     * Retrieves a review entity by its ID from the database.
     * @param id The ID of the review entity to retrieve.
     * @return Optional containing the review entity if found, otherwise empty.
     */
    Optional<ReviewEntity> findById(UUID id);

    /**
     * Retrieves all reviews associated with a specific movie from the database.
     * @param movieId The ID of the movie.
     * @return List of reviews associated with the specified movie.
     */
    List<ReviewEntity> findAllByMovieId(UUID movieId);

    /**
     * Retrieves all reviews submitted by a specific user from the database.
     * @param userId The ID of the user.
     * @return List of reviews submitted by the specified user.
     */
    List<ReviewEntity> findAllByUserId(UUID userId);

    /**
     * Retrieves all reviews with a rating greater than the specified value from the database.
     * @param rating The minimum rating for reviews to retrieve.
     * @return List of reviews with a rating greater than the specified value.
     */
    List<ReviewEntity> findAllByRatingGreaterThan(double rating);

    /**
     * Deletes a review entity by its ID from the database.
     * @param id The ID of the review entity to delete.
     */
    void deleteById(UUID id);

}
