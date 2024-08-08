package com.example.movieticketstoremgmtbackend.mapper;

import com.example.movieticketstoremgmtbackend.dto.MovieRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.MovieResponseDTO;
import com.example.movieticketstoremgmtbackend.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for mapping between MovieEntity and its corresponding DTOs.
 */
@Mapper
public interface MovieMapper {
    /**
     * Maps a MovieEntity to its corresponding MovieResponseDTO.
     *
     * @param movieEntity The MovieEntity to map.
     * @return The mapped MovieResponseDTO.
     */
    @Mapping(target = "ticketPrice", source = "ticketPrice")
    MovieResponseDTO movieEntityToMovieResponseDTO(MovieEntity movieEntity);

    /**
     * Maps a list of MovieEntity objects to a list of MovieResponseDTO objects.
     *
     * @param movieEntityList The list of MovieEntity objects to map.
     * @return The list of mapped MovieResponseDTO objects.
     */
    List<MovieResponseDTO> movieEntityListToMovieResponseDTOList(List<MovieEntity> movieEntityList);

    /**
     * Maps a MovieRequestDTO to its corresponding MovieEntity.
     *
     * @param movieRequestDTO The MovieRequestDTO to map.
     * @return The mapped MovieEntity.
     */
    MovieEntity movieRequestDTOToMovieEntity(MovieRequestDTO movieRequestDTO);
}
