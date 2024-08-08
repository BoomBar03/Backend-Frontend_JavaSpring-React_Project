package com.example.movieticketstoremgmtbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/**
 * Data Transfer Object (DTO) representing the response for a cinema bar.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaBarResponseDTO {
    private UUID id;
    private String menu;
    private String description;
    private double price;
}
