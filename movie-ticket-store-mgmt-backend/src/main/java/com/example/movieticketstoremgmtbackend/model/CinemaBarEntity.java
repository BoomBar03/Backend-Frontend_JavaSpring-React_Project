package com.example.movieticketstoremgmtbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Represents a CinemaBar entity in the database.
 */
@Entity
@Table(name = "CinemaBar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinemaBarEntity {
    /**
     * The unique identifier for the CinemaBar products.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * The menus offered by the Cinema's Bar.
     */
    @Column(name = "MENU")
    private String menu;

    /**
     * The description of the menus.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * The price of items offered by the Cinema's Bar.
     */
    @Column(name = "PRICE")
    private double price;
}
