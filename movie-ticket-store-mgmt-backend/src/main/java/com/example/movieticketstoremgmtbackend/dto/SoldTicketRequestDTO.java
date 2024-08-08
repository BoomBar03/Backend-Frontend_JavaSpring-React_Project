package com.example.movieticketstoremgmtbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing a request to sell tickets.
 * This DTO contains the unique identifiers of the user and employee, the movie being purchased,
 * the quantity of tickets, and the total price.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoldTicketRequestDTO {
    private UUID userId;
    private UUID employeeId;
    private UUID movieId;
    private int quantity;
    private double total;
}
