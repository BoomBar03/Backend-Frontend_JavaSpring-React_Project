package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionCode;
import com.example.movieticketstoremgmtbackend.exception.NotFoundException;
import com.example.movieticketstoremgmtbackend.repository.MovieRepository;
import com.example.movieticketstoremgmtbackend.dto.MovieRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.MovieResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.movieticketstoremgmtbackend.mapper.MovieMapper;
import com.example.movieticketstoremgmtbackend.model.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link MovieService} interface.
 */
@Slf4j
@RequiredArgsConstructor
public class MovieServiceBean implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final String applicationName;

    /**
     * Retrieves a movie by its ID.
     *
     * @param movieId The ID of the movie to retrieve.
     * @return The {@link MovieResponseDTO} representing the movie.
     * @throws NotFoundException if the movie with the specified ID is not found.
     */
    @Override
    public MovieResponseDTO findById(UUID movieId) {
        return movieRepository.findById(movieId)
                .map(movieMapper::movieEntityToMovieResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR002_MOVIE_NOT_FOUND.getMessage(),
                        movieId
                )));
    }

    /**
     * Retrieves all movies.
     *
     * @return A list of {@link MovieResponseDTO} representing all movies.
     * @throws NotFoundException if no movies are found.
     */
    @Override
    public List<MovieResponseDTO> findAll() {
        log.info("Getting all movies for application {}", applicationName);
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        if (movieEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_MOVIES_FOUND.getMessage())
            );
        }
        return movieMapper.movieEntityListToMovieResponseDTOList(movieEntityList);
    }

    /**
     * Retrieves movies in a paged manner.
     *
     * @param page The pagination information.
     * @return A {@link CollectionResponseDTO} containing a list of {@link MovieResponseDTO} and the total count.
     * @throws NotFoundException if no movies are found.
     */
    @Override
    public CollectionResponseDTO<MovieResponseDTO> findAllPaged(PageRequestDTO page) {
        Page<MovieEntity> movieEntityPage = movieRepository.findAll(PageRequest.of(
                page.getPageNumber(),
                page.getPageSize()
        ));
        List<MovieResponseDTO> movies = movieMapper.movieEntityListToMovieResponseDTOList(movieEntityPage.getContent());
        if (movies.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_MOVIES_FOUND.getMessage())
            );
        }
        return new CollectionResponseDTO<>(movies, movieEntityPage.getTotalElements());
    }

    /**
     * Retrieves all movies sorted by a specified field.
     *
     * @param sortBy The field to sort by.
     * @return A list of {@link MovieResponseDTO} representing all movies sorted by the specified field.
     * @throws NotFoundException if no movies are found.
     */
    @Override
    public List<MovieResponseDTO> findAllSorted(String sortBy) {
        List<MovieEntity> movieEntityList = movieRepository.findAll(Sort.by(sortBy).descending());
        if (movieEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_MOVIES_FOUND.getMessage())
            );
        }
        return movieMapper.movieEntityListToMovieResponseDTOList(movieEntityList);
    }

    /**
     * Retrieves movies with ticket prices greater than a specified value.
     *
     * @param ticketPrice The minimum ticket price.
     * @return A list of {@link MovieResponseDTO} representing movies with ticket prices greater than the specified value.
     * @throws NotFoundException if no movies are found.
     */
    @Override
    public List<MovieResponseDTO> findAllByTicketPriceGreaterThan(double ticketPrice) {
        if (ticketPrice < 0) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_TICKET_MUST_BE_POSITIVE_NUMBER.getMessage()));
        }

        List<MovieEntity> movieEntityList = movieRepository.findAllByTicketPriceGreaterThan(ticketPrice);
        if (movieEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_MOVIES_FOUND.getMessage())
            );
        }

        return movieMapper.movieEntityListToMovieResponseDTOList(movieEntityList);

    }

    /**
     * Saves a new movie.
     *
     * @param movieRequestDTO The data of the movie to be saved.
     * @return The {@link MovieResponseDTO} representing the saved movie.
     * @throws NotFoundException if the movie data is invalid.
     */
    @Override
    @Transactional
    public MovieResponseDTO save(MovieRequestDTO movieRequestDTO) {
        MovieEntity movieToBeAdded = movieMapper.movieRequestDTOToMovieEntity(movieRequestDTO);
        if (movieToBeAdded.getId() == null &&
                movieToBeAdded.getGenre() == null &&
                movieToBeAdded.getTitle() == null &&
                movieToBeAdded.getTicketPrice() < 0) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_MOVIE.getMessage())
            );

        }
        MovieEntity movieAdded = movieRepository.save(movieToBeAdded);
        return movieMapper.movieEntityToMovieResponseDTO(movieAdded);

    }
}
