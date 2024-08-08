package com.example.movieticketstoremgmtbackend.repository;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketResponseDTO;
import com.example.movieticketstoremgmtbackend.model.SoldTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Repository interface for accessing and managing SoldTicketEntity objects in the database.
 */
@Repository
public interface SoldTicketRepository extends JpaRepository<SoldTicketEntity, UUID> {

    /**
     * Retrieves all sold tickets from the database.
     * @return List of all sold tickets.
     */
    List<SoldTicketEntity> findAll();

    /**
     * Retrieves all sold tickets associated with a specific user from the database.
     * @param userId The ID of the user.
     * @return List of sold tickets associated with the specified user.
     */
    List<SoldTicketEntity> findAllByUserId(UUID userId);

    /**
     * Retrieves all sold tickets associated with a specific employee from the database.
     * @param employeeId The ID of the employee.
     * @return List of sold tickets associated with the specified employee.
     */
    List<SoldTicketEntity> findAllByEmployeeId(UUID employeeId);

    /**
     * Retrieves all sold tickets associated with a specific movie from the database.
     * @param movieId The ID of the movie.
     * @return List of sold tickets associated with the specified movie.
     */
    List<SoldTicketEntity> findAllByMovieId(UUID movieId);

    /**
     * Retrieves a sold ticket entity by its ID from the database.
     * @param ticketId The ID of the sold ticket entity to retrieve.
     * @return Optional containing the sold ticket entity if found, otherwise empty.
     */
    Optional<SoldTicketEntity> findById(UUID ticketId);

    /**
     * Deletes a sold ticket entity by its ID from the database.
     * @param id The ID of the sold ticket entity to delete.
     */
    void deleteById(UUID id);

}
