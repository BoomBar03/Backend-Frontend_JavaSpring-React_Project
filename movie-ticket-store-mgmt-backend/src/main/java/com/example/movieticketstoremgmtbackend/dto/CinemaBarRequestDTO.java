package com.example.movieticketstoremgmtbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) representing a request to create or update a cinema bar.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaBarRequestDTO {
    private String menu;
    private String description;
    private double price;
}
