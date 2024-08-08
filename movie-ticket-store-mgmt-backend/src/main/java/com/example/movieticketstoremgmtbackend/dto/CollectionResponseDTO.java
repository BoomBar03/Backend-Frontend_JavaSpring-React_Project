package com.example.movieticketstoremgmtbackend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data Transfer Object (DTO) representing a collection response.
 * This DTO is used to wrap a list of items along with total count.
 * @param <T> The type of items in the collection.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResponseDTO<T> {
    private List<T> items;
    private long total;
}
