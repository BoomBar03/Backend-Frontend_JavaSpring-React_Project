package com.example.movieticketstoremgmtbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing a request to create a review.
 * This DTO contains the IDs of the user and movie being reviewed, along with the rating given.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
    private UUID userId;
    private UUID movieId;
    private double rating;
}
