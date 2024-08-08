package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;

import java.util.List;
import java.util.UUID;
/**
 * Service interface for managing cinema bar entities.
 */
public interface CinemaBarService {

    /**
     * Retrieves a cinema bar entity by its ID.
     * @param cinemaBarId The ID of the cinema bar entity to retrieve.
     * @return The response DTO containing the cinema bar details.
     */
    CinemaBarResponseDTO findById(UUID cinemaBarId);

    /**
     * Retrieves all cinema bar entities.
     * @return List of cinema bar response DTOs.
     */
    List<CinemaBarResponseDTO> findAll();

    /**
     * Retrieves cinema bar entities in a paged manner.
     * @param page The page request DTO containing pagination details.
     * @return Collection response DTO containing a list of cinema bar response DTOs for the requested page.
     */
    CollectionResponseDTO<CinemaBarResponseDTO> findAllPaged(PageRequestDTO page);

    /**
     * Saves a new cinema bar entity.
     * @param cinemaBarRequestDTO The request DTO containing the details of the cinema bar to be saved.
     * @return The response DTO containing the details of the saved cinema bar.
     */
    CinemaBarResponseDTO save(CinemaBarRequestDTO cinemaBarRequestDTO);
}
