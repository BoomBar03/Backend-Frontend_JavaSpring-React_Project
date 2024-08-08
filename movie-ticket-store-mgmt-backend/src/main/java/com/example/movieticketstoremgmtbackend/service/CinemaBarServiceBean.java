package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionCode;
import com.example.movieticketstoremgmtbackend.exception.NotFoundException;
import com.example.movieticketstoremgmtbackend.repository.CinemaBarRepository;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.CinemaBarResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.movieticketstoremgmtbackend.mapper.CinemaBarMapper;
import com.example.movieticketstoremgmtbackend.model.CinemaBarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link CinemaBarService} interface.
 */
@Slf4j
@RequiredArgsConstructor
public class CinemaBarServiceBean implements CinemaBarService {

    private final CinemaBarRepository cinemaBarRepository;
    private final CinemaBarMapper cinemaBarMapper;
    private final String applicationName;

    /**
     * Retrieves a cinema bar menu by its ID.
     *
     * @param cinemaBarId The ID of the cinema bar to retrieve.
     * @return The {@link CinemaBarResponseDTO} representing the cinema bar.
     * @throws NotFoundException if the cinema bar with the specified ID is not found.
     */
    @Override
    public CinemaBarResponseDTO findById(UUID cinemaBarId) {
        return cinemaBarRepository.findById(cinemaBarId)
                .map(cinemaBarMapper::cinemaBarEntityToResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR_CINEMABAR_MENU_NOT_FOUND.getMessage(),
                        cinemaBarId
                )));
    }

    /**
     * Retrieves all cinema bar's menus.
     *
     * @return A list of {@link CinemaBarResponseDTO} representing all cinema bar's menus.
     * @throws NotFoundException if no cinema bar's menus are found.
     */
    @Override
    public List<CinemaBarResponseDTO> findAll() {
        log.info("Getting all cinema bars for application {}", applicationName);
        List<CinemaBarEntity> cinemaBarEntityList = cinemaBarRepository.findAll();
        if (cinemaBarEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_CINEMABAR_MENU_FOUND.getMessage())
            );
        }
        return cinemaBarMapper.cinemaBarEntityListToResponseDTOList(cinemaBarEntityList);
    }

    /**
     * Retrieves cinema bar's menus in a paged manner.
     *
     * @param page The pagination information.
     * @return A {@link CollectionResponseDTO} containing a list of {@link CinemaBarResponseDTO} and the total count.
     * @throws NotFoundException if no cinema bars menus are found.
     */
    @Override
    public CollectionResponseDTO<CinemaBarResponseDTO> findAllPaged(PageRequestDTO page) {
        Page<CinemaBarEntity> cinemaBarEntityPage = cinemaBarRepository.findAll(PageRequest.of(
                page.getPageNumber(),
                page.getPageSize()
        ));
        List<CinemaBarResponseDTO> cinemaBars = cinemaBarMapper.cinemaBarEntityListToResponseDTOList(cinemaBarEntityPage.getContent());
        if (cinemaBars.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_CINEMABAR_MENU_FOUND.getMessage())
            );
        }
        return new CollectionResponseDTO<>(cinemaBars, cinemaBarEntityPage.getTotalElements());
    }

    /**
     * Saves a new cinema bar menu.
     *
     * @param cinemaBarRequestDTO The data of the cinema bar menu to be saved.
     * @return The {@link CinemaBarResponseDTO} representing the saved cinema bar menu.
     * @throws NotFoundException if the provided cinema bar menu data is invalid.
     */
    @Override
    public CinemaBarResponseDTO save(CinemaBarRequestDTO cinemaBarRequestDTO) {
        CinemaBarEntity cinemaBarToBeAdded = cinemaBarMapper.requestDTOToEntity(cinemaBarRequestDTO);
        if (cinemaBarToBeAdded.getId() == null &&
                cinemaBarToBeAdded.getMenu() == null &&
                cinemaBarToBeAdded.getDescription() == null &&
                cinemaBarToBeAdded.getPrice() < 0) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_CINEMABAR_MENU.getMessage())
            );
        }
        CinemaBarEntity cinemaBarAdded = cinemaBarRepository.save(cinemaBarToBeAdded);
        return cinemaBarMapper.cinemaBarEntityToResponseDTO(cinemaBarAdded);
    }
}
