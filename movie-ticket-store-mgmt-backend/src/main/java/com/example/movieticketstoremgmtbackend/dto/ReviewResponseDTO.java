package com.example.movieticketstoremgmtbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing a response containing review details.
 * This DTO contains the unique identifier of the review, user ID, movie ID, and the rating.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private UUID id;
    private UUID userId;
    private UUID movieId;
    private double rating;
}
