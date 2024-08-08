package com.example.movieticketstoremgmtbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a Movie entity in the database.
 */
@Entity
@Table(name = "Movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    /**
     * The unique identifier for the movie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The title of the movie.
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * The price of a ticket for the movie.
     */
    @Column(name = "TICKET_PRICE")
    private double ticketPrice;

    /**
     * The genre of the movie.
     */
    @Column(name = "GENRE")
    private String genre;

    @Column(name = "DETAILS")
    private String details;
}
