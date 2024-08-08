package com.example.movieticketstoremgmtbackend.controller;


import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionBody;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.MovieRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.MovieResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.movieticketstoremgmtbackend.service.MovieService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
@CrossOrigin("*")
@RestController
@RequestMapping("/movie/v1")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID", description = "Movie must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MovieResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable("id") UUID movieId) {
        return new ResponseEntity<>(
                movieService.findById(movieId),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @Operation(summary = "Get all movies", description = "Retrieves all movies.")
    public ResponseEntity<List<MovieResponseDTO>> findAll() {
        return new ResponseEntity<>(
                movieService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-paged")
    @Operation(summary = "Get movies with pagination", description = "Retrieves movies with pagination.")
    public ResponseEntity<CollectionResponseDTO<MovieResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(
                movieService.findAllPaged(page),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-sorted")
    @Operation(summary = "Get all movies sorted", description = "Retrieves all movies sorted by a specific field.")
    public ResponseEntity<List<MovieResponseDTO>> findAllSorted(
            @RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        return new ResponseEntity<>(
                movieService.findAllSorted(sortBy),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-by-ticket-price")
    @Operation(summary = "Get movies by ticket price", description = "Retrieves all movies with a ticket price greater than the specified value.")
    public ResponseEntity<List<MovieResponseDTO>> findAllByTicketPriceGreaterThan(
            @RequestParam("ticketPrice") double ticketPrice
    ) {
        return new ResponseEntity<>(
                movieService.findAllByTicketPriceGreaterThan(ticketPrice),
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new movie", description = "Creates a new movie.")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<MovieResponseDTO> save(
            @RequestBody MovieRequestDTO movieRequestDTO
    ) {
        return new ResponseEntity<>(
                movieService.save(movieRequestDTO),
                HttpStatus.CREATED
        );
    }
}

