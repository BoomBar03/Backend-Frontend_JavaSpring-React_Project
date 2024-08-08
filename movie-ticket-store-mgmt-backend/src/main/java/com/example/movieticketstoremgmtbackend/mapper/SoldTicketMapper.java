package com.example.movieticketstoremgmtbackend.mapper;

import com.example.movieticketstoremgmtbackend.dto.SoldTicketResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketRequestDTO;
import com.example.movieticketstoremgmtbackend.model.SoldTicketEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for mapping between SoldTicketEntity and its corresponding DTOs.
 */
@Mapper
public interface SoldTicketMapper {

    /**
     * Maps a SoldTicketEntity to its corresponding SoldTicketResponseDTO.
     *
     * @param soldTicketEntity The SoldTicketEntity to map.
     * @return The mapped SoldTicketResponseDTO.
     */
    SoldTicketResponseDTO soldTicketEntityToSoldTicketResponseDTO(SoldTicketEntity soldTicketEntity);

    /**
     * Maps a list of SoldTicketEntity objects to a list of SoldTicketResponseDTO objects.
     *
     * @param soldTicketEntityList The list of SoldTicketEntity objects to map.
     * @return The list of mapped SoldTicketResponseDTO objects.
     */
    List<SoldTicketResponseDTO> soldTicketEntityListToSoldTicketResponseDTOList(List<SoldTicketEntity> soldTicketEntityList);

    /**
     * Maps a SoldTicketRequestDTO to its corresponding SoldTicketEntity.
     *
     * @param soldTicketRequestDTO The SoldTicketRequestDTO to map.
     * @return The mapped SoldTicketEntity.
     */
    SoldTicketEntity soldTicketRequestDTOToSoldTicketEntity(SoldTicketRequestDTO soldTicketRequestDTO);
}