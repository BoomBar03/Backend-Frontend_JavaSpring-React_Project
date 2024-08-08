package com.example.movieticketstoremgmtbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing a response containing movie information.
 * This DTO contains the ID, title, ticket price, and genre of a movie.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {
    private UUID id;
    private String title;
    private double ticketPrice;
    private String genre;
    private String details;
}
