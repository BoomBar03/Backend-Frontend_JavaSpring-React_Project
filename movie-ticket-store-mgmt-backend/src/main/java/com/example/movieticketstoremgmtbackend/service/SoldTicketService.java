package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketResponseDTO;


import java.util.List;
import java.util.UUID;

public interface SoldTicketService {

    SoldTicketResponseDTO findById(UUID ticketId);

    List<SoldTicketResponseDTO> findAll();
    List<SoldTicketResponseDTO> findAllSorted(String sortBy);

    CollectionResponseDTO<SoldTicketResponseDTO> findAllPaged(PageRequestDTO page);

    List<SoldTicketResponseDTO> findAllByUserId(UUID userId);

    List<SoldTicketResponseDTO> findAllByEmployeeId(UUID employeeId);

    List<SoldTicketResponseDTO> findAllByMovieId(UUID movieId);

    SoldTicketResponseDTO save(SoldTicketRequestDTO soldTicketRequestDTO);

    void deleteById(UUID ticketId);
}
