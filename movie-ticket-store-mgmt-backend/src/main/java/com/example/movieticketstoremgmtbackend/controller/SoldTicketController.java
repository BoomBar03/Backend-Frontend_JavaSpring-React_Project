package com.example.movieticketstoremgmtbackend.controller;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketResponseDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionBody;
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
import com.example.movieticketstoremgmtbackend.service.SoldTicketService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/sold-ticket/v1")
@RequiredArgsConstructor
public class SoldTicketController {

    private final SoldTicketService soldTicketService;

    @GetMapping("/{id}")
    @Operation(summary = "Gets sold ticket by ID", description = "Sold ticket must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sold ticket found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = SoldTicketResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Sold ticket not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<SoldTicketResponseDTO> findById(@PathVariable("id") UUID soldTicketId) {
        return new ResponseEntity<>(
                soldTicketService.findById(soldTicketId),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @Operation(summary = "Get all sold tickets", description = "Retrieves all sold tickets.")
    public ResponseEntity<List<SoldTicketResponseDTO>> findAll() {
        return new ResponseEntity<>(
                soldTicketService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-paged")
    @Operation(summary = "Get sold tickets with pagination", description = "Retrieves sold tickets with pagination.")
    public ResponseEntity<CollectionResponseDTO<SoldTicketResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(
                soldTicketService.findAllPaged(page),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-sorted")
    @Operation(summary = "Get all sold tickets sorted", description = "Retrieves all sold tickets sorted by a specific field.")
    public ResponseEntity<List<SoldTicketResponseDTO>> findAllSorted(
            @RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        return new ResponseEntity<>(
                soldTicketService.findAllSorted(sortBy),
                HttpStatus.OK
        );
    }

    @GetMapping("/by-user/{userId}")
    @Operation(summary = "Get sold tickets by user ID", description = "Retrieves all sold tickets for a given user ID.")
    public ResponseEntity<List<SoldTicketResponseDTO>> findAllByUserId(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(
                soldTicketService.findAllByUserId(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("/by-movie/{movieId}")
    @Operation(summary = "Get sold tickets by movie ID", description = "Retrieves all sold tickets for a given movie ID.")
    public ResponseEntity<List<SoldTicketResponseDTO>> findAllByMovieId(@PathVariable("movieId") UUID movieId) {
        return new ResponseEntity<>(
                soldTicketService.findAllByMovieId(movieId),
                HttpStatus.OK
        );
    }

    @PostMapping("/all")
    @Operation(summary = "Save a new sold ticket", description = "Creates a new sold ticket.")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<SoldTicketResponseDTO> save(
            @RequestBody SoldTicketRequestDTO soldTicketRequestDTO
    ) {
        return new ResponseEntity<>(
                soldTicketService.save(soldTicketRequestDTO),
                HttpStatus.CREATED
        );
    }
}
