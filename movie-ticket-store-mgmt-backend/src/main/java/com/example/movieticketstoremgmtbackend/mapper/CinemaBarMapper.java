package com.example.movieticketstoremgmtbackend.mapper;

import com.example.movieticketstoremgmtbackend.dto.CinemaBarRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarResponseDTO;
import com.example.movieticketstoremgmtbackend.model.CinemaBarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for mapping between CinemaBarEntity and its corresponding DTOs.
 */
@Mapper
public interface CinemaBarMapper {
    /**
     * Maps a CinemaBarEntity to its corresponding CinemaBarResponseDTO.
     *
     * @param cinemaBarEntity The CinemaBarEntity to map.
     * @return The mapped CinemaBarResponseDTO.
     */
    CinemaBarResponseDTO cinemaBarEntityToResponseDTO(CinemaBarEntity cinemaBarEntity);

    /**
     * Maps a list of CinemaBarEntity objects to a list of CinemaBarResponseDTO objects.
     *
     * @param cinemaBarEntityList The list of CinemaBarEntity objects to map.
     * @return The list of mapped CinemaBarResponseDTO objects.
     */
    List<CinemaBarResponseDTO> cinemaBarEntityListToResponseDTOList(List<CinemaBarEntity> cinemaBarEntityList);

    /**
     * Maps a CinemaBarRequestDTO to its corresponding CinemaBarEntity.
     *
     * @param cinemaBarRequestDTO The CinemaBarRequestDTO to map.
     * @return The mapped CinemaBarEntity.
     */
    CinemaBarEntity requestDTOToEntity(CinemaBarRequestDTO cinemaBarRequestDTO);
}
