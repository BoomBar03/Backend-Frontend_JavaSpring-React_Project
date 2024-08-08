package com.example.movieticketstoremgmtbackend.controller;


import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionBody;
import com.example.movieticketstoremgmtbackend.dto.ReviewRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.movieticketstoremgmtbackend.service.ReviewService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/review/v1")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    @Operation(summary = "Gets review by ID", description = "Review must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Review not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<ReviewResponseDTO> findById(@PathVariable("id") UUID reviewId) {
        return new ResponseEntity<>(
                reviewService.findById(reviewId),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    @Operation(summary = "Get all reviews", description = "Retrieves all reviews.")
    public ResponseEntity<List<ReviewResponseDTO>> findAll() {
        return new ResponseEntity<>(
                reviewService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-paged")
    @Operation(summary = "Get reviews with pagination", description = "Retrieves reviews with pagination.")
    public ResponseEntity<CollectionResponseDTO<ReviewResponseDTO>> findAllPaged(@Valid PageRequestDTO page) {
        return new ResponseEntity<>(
                reviewService.findAllPaged(page),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-sorted")
    @Operation(summary = "Get all reviews sorted", description = "Retrieves all reviews sorted by a specific field.")
    public ResponseEntity<List<ReviewResponseDTO>> findAllSorted(
            @RequestParam(value = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        return new ResponseEntity<>(
                reviewService.findAllSorted(sortBy),
                HttpStatus.OK
        );
    }

    @GetMapping("/by-user/{userId}")
    @Operation(summary = "Get reviews by user ID", description = "Retrieves all reviews for a given user ID.")
    public ResponseEntity<List<ReviewResponseDTO>> findByUserId(@PathVariable("userId") UUID userId) {
        return new ResponseEntity<>(
                reviewService.findAllByUserId(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("/by-movie/{movieId}")
    @Operation(summary = "Get reviews by movie ID", description = "Retrieves all reviews for a given movie ID.")
    public ResponseEntity<List<ReviewResponseDTO>> findByMovieId(@PathVariable("movieId") UUID movieId) {
        return new ResponseEntity<>(
                reviewService.findAllByMovieId(movieId),
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    @Operation(summary = "Save a new review", description = "Creates a new review.")
    public ResponseEntity<ReviewResponseDTO> save(
            @RequestBody ReviewRequestDTO reviewRequestDTO
    ) {
        return new ResponseEntity<>(
                reviewService.save(reviewRequestDTO),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all-ratings/{rating}")
    @Operation(summary = "Get reviews by rating", description = "Retrieves all reviews with a rating greater than the specified value.")
    public ResponseEntity<List<ReviewResponseDTO>> findAllByRating(
            @PathVariable("rating") double rating
    ) {
        return new ResponseEntity<>(
                reviewService.findAllByRatingGreaterThan(rating),
                HttpStatus.OK
        );
    }

    @GetMapping("/all-ratings")
    @Operation(summary = "Get reviews by rating", description = "Retrieves all reviews with a rating greater than the specified value.")
    public ResponseEntity<List<ReviewResponseDTO>> findAllByRating2(
            @RequestParam(value = "rating", defaultValue = "10", required = false) double rating
    ) {
        return new ResponseEntity<>(
                reviewService.findAllByRatingGreaterThan(rating),
                HttpStatus.OK
        );
    }
}

