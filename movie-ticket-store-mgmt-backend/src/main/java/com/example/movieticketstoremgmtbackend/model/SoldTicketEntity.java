package com.example.movieticketstoremgmtbackend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a sold ticket entity in the database.
 */
@Entity
@Table(name = "SoldTicket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoldTicketEntity {

    /**
     * The unique identifier for the sold ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The ID of the employee who sold the ticket.
     */
    @Column(name = "EMPLOYEE_ID")
    private UUID employeeId;

    /**
     * The ID of the user who purchased the ticket.
     */
    @Column(name = "USER_ID")
    private UUID userId;

    /**
     * The ID of the movie for which the ticket was sold.
     */
    @Column(name = "MOVIE_ID")
    private UUID movieId;

    /**
     * The quantity of tickets sold.
     */
    @Column(name = "QUANTITY")
    private int quantity;

    /**
     * The total price of the sold tickets.
     */
    @Column(name = "TOTAL")
    private double total;
}
