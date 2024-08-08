package com.example.movieticketstoremgmtbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) representing a request to create or update a movie.
 * This DTO contains information such as the movie title, ticket price, and genre.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
    private String title;
    private double ticketPrice;
    private String genre;
    private String details;
}
