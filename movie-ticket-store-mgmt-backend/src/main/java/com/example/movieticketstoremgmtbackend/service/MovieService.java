package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.MovieRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.MovieResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    MovieResponseDTO findById(UUID movieId);

    List<MovieResponseDTO> findAll();

    CollectionResponseDTO<MovieResponseDTO> findAllPaged(PageRequestDTO page);

    List<MovieResponseDTO> findAllSorted(String sortBy);

    List<MovieResponseDTO> findAllByTicketPriceGreaterThan(double ticketPrice);

    MovieResponseDTO save(MovieRequestDTO movieRequestDTO);
}
