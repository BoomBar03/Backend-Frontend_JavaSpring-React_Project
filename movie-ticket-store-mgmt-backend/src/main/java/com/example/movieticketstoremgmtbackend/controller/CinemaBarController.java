package com.example.movieticketstoremgmtbackend.controller;

import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarResponseDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionBody;
import com.example.movieticketstoremgmtbackend.service.CinemaBarService;
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

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cinema-bar/v1")
@RequiredArgsConstructor
public class CinemaBarController {

    private final CinemaBarService cinemaBarService;

    @GetMapping("/{id}")
    @Operation(summary = "Get cinema bar by ID", description = "Cinema bar must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cinema bar found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CinemaBarResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Cinema bar not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<CinemaBarResponseDTO> findById(@PathVariable("id") UUID cinemaBarId) {
        return new ResponseEntity<>(
                cinemaBarService.findById(cinemaBarId),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @Operation(summary = "Get all cinema bars", description = "Retrieves all cinema bars.")
    public ResponseEntity<List<CinemaBarResponseDTO>> findAll() {
        return new ResponseEntity<>(
                cinemaBarService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-paged")
    @Operation(summary = "Get cinema bars with pagination", description = "Retrieves cinema bars with pagination.")
    public ResponseEntity<CollectionResponseDTO<CinemaBarResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(
                cinemaBarService.findAllPaged(page),
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new cinema bar", description = "Creates a new cinema bar.")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<CinemaBarResponseDTO> save(
            @RequestBody CinemaBarRequestDTO cinemaBarRequestDTO
    ) {
        return new ResponseEntity<>(
                cinemaBarService.save(cinemaBarRequestDTO),
                HttpStatus.CREATED
        );
    }
}
