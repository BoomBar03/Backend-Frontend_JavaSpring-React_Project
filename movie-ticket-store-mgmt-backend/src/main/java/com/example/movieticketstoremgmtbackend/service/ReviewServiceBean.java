package com.example.movieticketstoremgmtbackend.service;

import com.example.movieticketstoremgmtbackend.dto.CollectionResponseDTO;
import com.example.movieticketstoremgmtbackend.dto.PageRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.ReviewRequestDTO;
import com.example.movieticketstoremgmtbackend.dto.ReviewResponseDTO;
import com.example.movieticketstoremgmtbackend.exception.ExceptionCode;
import com.example.movieticketstoremgmtbackend.exception.NotFoundException;
import com.example.movieticketstoremgmtbackend.mapper.ReviewMapper;
import com.example.movieticketstoremgmtbackend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.movieticketstoremgmtbackend.model.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link ReviewService} interface.
 */
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceBean implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final String applicationName;

    /**
     * Retrieves a review by its ID.
     *
     * @param reviewId The ID of the review to retrieve.
     * @return The {@link ReviewResponseDTO} representing the review.
     * @throws NotFoundException if the review with the specified ID is not found.
     */
    @Override
    public ReviewResponseDTO findById(UUID reviewId) {
        return reviewRepository.findById(reviewId)
                .map(reviewMapper::reviewEntityToReviewResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR003_REVIEW_NOT_FOUND.getMessage(),
                        reviewId
                )));
    }

    /**
     * Retrieves all reviews.
     *
     * @return A list of {@link ReviewResponseDTO} representing all reviews.
     * @throws NotFoundException if no reviews are found.
     */
    @Override
    public List<ReviewResponseDTO> findAll() {
        log.info("Getting all reviews for application {}", applicationName);
        List<ReviewEntity> reviewEntityList = reviewRepository.findAll();
        if (reviewEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_REVIEWS_FOUND.getMessage()
            ));
        }
        return reviewMapper.reviewEntityListToReviewResponseDTOList(reviewEntityList);
    }

    /**
     * Retrieves reviews in a paged manner.
     *
     * @param page The pagination information.
     * @return A {@link CollectionResponseDTO} containing a list of {@link ReviewResponseDTO} and the total count.
     * @throws NotFoundException if no reviews are found.
     */
    @Override
    public CollectionResponseDTO<ReviewResponseDTO> findAllPaged(PageRequestDTO page) {
        Page<ReviewEntity> reviewEntityPage = reviewRepository.findAll(PageRequest.of(
                page.getPageNumber(),
                page.getPageSize()
        ));
        List<ReviewResponseDTO> reviews = reviewMapper.reviewEntityListToReviewResponseDTOList(reviewEntityPage.getContent());
        if (reviews.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_REVIEWS_FOUND.getMessage()
            ));
        }
        return new CollectionResponseDTO<>(reviews, reviewEntityPage.getTotalElements());
    }

    /**
     * Retrieves all reviews sorted by a specified field.
     *
     * @param sortBy The field to sort by.
     * @return A list of {@link ReviewResponseDTO} representing all reviews sorted by the specified field.
     * @throws NotFoundException if no reviews are found.
     */
    @Override
    public List<ReviewResponseDTO> findAllSorted(String sortBy) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findAll(Sort.by(sortBy).descending());
        if (reviewEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_REVIEWS_FOUND.getMessage()
            ));
        }
        return reviewMapper.reviewEntityListToReviewResponseDTOList(reviewEntityList);
    }


    /**
     * Retrieves reviews by user ID.
     *
     * @param userId The ID of the user.
     * @return A list of {@link ReviewResponseDTO} representing reviews by the specified user.
     * @throws NotFoundException if no reviews are found for the specified user ID.
     */
    @Override
    public List<ReviewResponseDTO> findAllByUserId(UUID userId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByUserId(userId);
        if (reviewEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_REVIEWS_FOUND.getMessage()
            ));
        }
        return reviewMapper.reviewEntityListToReviewResponseDTOList(reviewEntityList);
    }

    /**
     * Retrieves reviews by movie ID.
     *
     * @param movieId The ID of the movie.
     * @return A list of {@link ReviewResponseDTO} representing reviews for the specified movie.
     * @throws NotFoundException if no reviews are found for the specified movie ID.
     */
    @Override
    public List<ReviewResponseDTO> findAllByMovieId(UUID movieId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByMovieId(movieId);
        if (reviewEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_REVIEWS_FOUND.getMessage()
            ));
        }
        return reviewMapper.reviewEntityListToReviewResponseDTOList(reviewEntityList);
    }

    /**
     * Saves a new review.
     *
     * @param reviewRequestDTO The data of the review to be saved.
     * @return The {@link ReviewResponseDTO} representing the saved review.
     * @throws NotFoundException if the review data is invalid.
     */
    @Override
    @Transactional
    public ReviewResponseDTO save(ReviewRequestDTO reviewRequestDTO) {
        ReviewEntity reviewToBeAdded = reviewMapper.reviewRequestDTOToReviewEntity(reviewRequestDTO);
        if (reviewToBeAdded.getId() == null &&
                reviewToBeAdded.getMovieId() == null &&
                reviewToBeAdded.getUserId() == null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_INVALID_REVIEW.getMessage()));
        }

        ReviewEntity reviewAdded = reviewRepository.save(reviewToBeAdded);
        return reviewMapper.reviewEntityToReviewResponseDTO(reviewAdded);
    }

    /**
     * Retrieves reviews with ratings greater than a specified value.
     *
     * @param rating The minimum rating.
     * @return A list of {@link ReviewResponseDTO} representing reviews with ratings greater than the specified value.
     * @throws NotFoundException if no reviews are found with ratings greater than the specified value.
     */
    @Override
    public List<ReviewResponseDTO> findAllByRatingGreaterThan(double rating) {
        if (rating < 0) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_RATING_MUST_BE_POSITIVE_NUMBER.getMessage()));
        }
        List<ReviewEntity> reviewEntityList = reviewRepository.findAllByRatingGreaterThan(rating);
        if (reviewEntityList.isEmpty()) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR_NO_REVIEWS_FOUND.getMessage()
            ));
        }

        return reviewMapper.reviewEntityListToReviewResponseDTOList(reviewEntityList);
    }
}
