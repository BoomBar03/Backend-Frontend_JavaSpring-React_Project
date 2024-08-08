package com.example.movieticketstoremgmtbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a review entity in the database.
 */
@Entity
@Table(name = "Review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    /**
     * The unique identifier for the review.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The ID of the user who submitted the review.
     */
    @Column(name = "USER_ID")
    private UUID userId;

    /**
     * The ID of the movie being reviewed.
     */
    @Column(name = "MOVIE_ID")
    private UUID movieId;

    /**
     * The rating given by the user.
     */
    @Column(name = "RATING")
    private double rating;

}
