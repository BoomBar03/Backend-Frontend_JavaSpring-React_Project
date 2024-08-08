package com.example.movieticketstoremgmtbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing a response for sold tickets.
 * This DTO contains the unique identifier of the sold ticket, the user and employee IDs involved,
 * the movie ID, the quantity of tickets sold, and the total price.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoldTicketResponseDTO {
    private UUID id;
    private UUID userId;
    private UUID employeeId;
    private UUID movieId;
    private int quantity;
    private double total;
}
