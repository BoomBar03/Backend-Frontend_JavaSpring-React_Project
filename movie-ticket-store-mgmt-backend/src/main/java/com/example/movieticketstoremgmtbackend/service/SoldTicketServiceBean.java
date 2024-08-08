package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionCode;
import com.example.movieticketstoremgmtbackend.exception.NotFoundException;
import com.example.movieticketstoremgmtbackend.repository.SoldTicketRepository;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.SoldTicketResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.movieticketstoremgmtbackend.mapper.SoldTicketMapper;
import com.example.movieticketstoremgmtbackend.model.SoldTicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link SoldTicketService} interface.
 */
@Slf4j
@RequiredArgsConstructor
public class SoldTicketServiceBean implements SoldTicketService {

    private final SoldTicketRepository soldTicketRepository;
    private final SoldTicketMapper soldTicketMapper;
    private final String applicationName;

    /**
     * Retrieves a sold ticket by its ID.
     *
     * @param ticketId The ID of the sold ticket to retrieve.
     * @return The {@link SoldTicketResponseDTO} representing the sold ticket.
     * @throws NotFoundException if the sold ticket with the specified ID is not found.
     */
    @Override
    public SoldTicketResponseDTO findById(UUID ticketId) {
        return soldTicketRepository.findById(ticketId)
                .map(soldTicketMapper::soldTicketEntityToSoldTicketResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR004_TICKET_NOT_FOUND.getMessage(),
                        ticketId
                )));
    }

    /**
     * Retrieves all sold tickets.
     *
     * @return A list of {@link SoldTicketResponseDTO} representing all sold tickets.
     * @throws NotFoundException if no sold tickets are found.
     */
    @Override
    public List<SoldTicketResponseDTO> findAll() {
        log.info("Getting all sold tickets for application {}", applicationName);

        List<SoldTicketEntity> soldTicketList = soldTicketRepository.findAll();
        if (soldTicketList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_SOLD_TICKETS_FOUND.getMessage()
            ));
        }
        return soldTicketMapper.soldTicketEntityListToSoldTicketResponseDTOList(soldTicketList);
    }

    /**
     * Retrieves all sold tickets sorted by a specified field.
     *
     * @param sortBy The field to sort by.
     * @return A list of {@link SoldTicketResponseDTO} representing all sold tickets sorted by the specified field.
     * @throws NotFoundException if no sold tickets are found.
     */
    @Override
    public List<SoldTicketResponseDTO> findAllSorted(String sortBy) {
        List<SoldTicketEntity> soldTicketList = soldTicketRepository.findAll(Sort.by(sortBy).descending());
        if (soldTicketList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_SOLD_TICKETS_FOUND.getMessage()
            ));
        }
        return soldTicketMapper.soldTicketEntityListToSoldTicketResponseDTOList(soldTicketList);
    }

    /**
     * Retrieves sold tickets in a paged manner.
     *
     * @param page The pagination information.
     * @return A {@link CollectionResponseDTO} containing a list of {@link SoldTicketResponseDTO} and the total count.
     * @throws NotFoundException if no sold tickets are found.
     */
    @Override
    public CollectionResponseDTO<SoldTicketResponseDTO> findAllPaged(PageRequestDTO page) {
        Page<SoldTicketEntity> soldTicketEntityPage = soldTicketRepository.findAll(PageRequest.of(
                page.getPageNumber(),
                page.getPageSize()
        ));
        List<SoldTicketResponseDTO> soldTickets = soldTicketMapper.soldTicketEntityListToSoldTicketResponseDTOList(
                soldTicketEntityPage.getContent());
        if (soldTickets.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_SOLD_TICKETS_FOUND.getMessage()
            ));
        }
        return new CollectionResponseDTO<>(soldTickets, soldTicketEntityPage.getTotalElements());
    }

    /**
     * Retrieves sold tickets by user ID.
     *
     * @param userId The ID of the user.
     * @return A list of {@link SoldTicketResponseDTO} representing sold tickets by the specified user.
     * @throws NotFoundException if no sold tickets are found for the specified user ID.
     */
    @Override
    public List<SoldTicketResponseDTO> findAllByUserId(UUID userId) {
        List<SoldTicketEntity> soldTicketList = soldTicketRepository.findAllByUserId(userId);

        if (userId == null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_ID.getMessage()));
        }

        if (soldTicketList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_SOLD_TICKETS_FOUND.getMessage()
            ));
        }
        return soldTicketMapper.soldTicketEntityListToSoldTicketResponseDTOList(soldTicketList);
    }

    /**
     * Retrieves sold tickets by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of {@link SoldTicketResponseDTO} representing sold tickets by the specified employee.
     * @throws NotFoundException if no sold tickets are found for the specified employee ID.
     */
    @Override
    public List<SoldTicketResponseDTO> findAllByEmployeeId(UUID employeeId) {
        List<SoldTicketEntity> soldTicketList = soldTicketRepository.findAllByEmployeeId(employeeId);

        if (employeeId == null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_ID.getMessage()));
        }

        if (soldTicketList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_SOLD_TICKETS_FOUND.getMessage()
            ));
        }
        return soldTicketMapper.soldTicketEntityListToSoldTicketResponseDTOList(soldTicketList);
    }

    /**
     * Retrieves sold tickets by movie ID.
     *
     * @param movieId The ID of the movie.
     * @return A list of {@link SoldTicketResponseDTO} representing sold tickets for the specified movie.
     * @throws NotFoundException if no sold tickets are found for the specified movie ID.
     */
    @Override
    public List<SoldTicketResponseDTO> findAllByMovieId(UUID movieId) {
        List<SoldTicketEntity> soldTicketList = soldTicketRepository.findAllByMovieId(movieId);

        if (movieId == null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_ID.getMessage()));
        }
        if (soldTicketList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_SOLD_TICKETS_FOUND.getMessage()
            ));
        }

        return soldTicketMapper.soldTicketEntityListToSoldTicketResponseDTOList(soldTicketList);
    }

    /**
     * Saves a new sold ticket.
     *
     * @param soldTicketRequestDTO The DTO representing the sold ticket to be saved.
     * @return The {@link SoldTicketResponseDTO} representing the saved sold ticket.
     * @throws NotFoundException if the sold ticket is invalid.
     */
    @Override
    @Transactional
    public SoldTicketResponseDTO save(SoldTicketRequestDTO soldTicketRequestDTO) {
        SoldTicketEntity soldTicketToBeAdded = soldTicketMapper.soldTicketRequestDTOToSoldTicketEntity(
                soldTicketRequestDTO);

        if (soldTicketToBeAdded.getId() == null &&
                soldTicketToBeAdded.getEmployeeId() == null &&
                soldTicketToBeAdded.getMovieId() == null &&
                soldTicketToBeAdded.getUserId() == null &&
                soldTicketToBeAdded.getQuantity() <= 0 &&
                soldTicketToBeAdded.getTotal() == 0) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_SOLD_TICKET.getMessage()
            ));
        }

        SoldTicketEntity soldTicketAdded = soldTicketRepository.save(soldTicketToBeAdded);
        return soldTicketMapper.soldTicketEntityToSoldTicketResponseDTO(soldTicketAdded);
    }

    /**
     * Deletes a sold ticket by its ID.
     *
     * @param ticketId The ID of the sold ticket to delete.
     * @throws NotFoundException if the sold ticket with the specified ID is not found.
     */
    @Override
    public void deleteById(UUID ticketId) {
        if (ticketId == null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_ID.getMessage()
            ));
        }
        soldTicketRepository.deleteById(ticketId);
    }
}
